package com.multiwarehouse.app.authgateway.service.messaging.mapper;

import com.multiwarehouse.app.authgateway.service.domain.entity.User;
import com.multiwarehouse.app.authgateway.service.domain.entity.UserAdminWarehouse;
import com.multiwarehouse.app.authgateway.service.domain.valueobject.UserAdminWarehouseId;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel;
import com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel;
import org.springframework.stereotype.Component;

@Component
public class UserMessagingDataMapper {
    public User userFromUserChangeAvroModel(UserChangeAvroModel userChangeAvroModel) {
        UserId userId = new UserId(userChangeAvroModel.getUserId());
        User user = User.builder()
                .withId(new UserId(userChangeAvroModel.getUserId()))
                .withUsername(userChangeAvroModel.getUsername())
                .withEmail(userChangeAvroModel.getEmail())
                .withPassword(userChangeAvroModel.getPassword())
                .withRole(UserRole.valueOf(userChangeAvroModel.getRole().name()))
                .withActive(userChangeAvroModel.getActive())
                .withUserAdminWarehouse(userAdminWarehouseFromUserAdminWarehouseAvroModel(userChangeAvroModel.getUserAdminWarehouse()))
                .build();
        if (user.getUserAdminWarehouse() != null) user.getUserAdminWarehouse().setUserId(userId);
        return user;
    }

    private UserAdminWarehouse userAdminWarehouseFromUserAdminWarehouseAvroModel(UserAdminWarehouseAvroModel userAdminWarehouseAvroModel) {
        if (userAdminWarehouseAvroModel == null) return null;
        return UserAdminWarehouse.builder()
                .withId(new UserAdminWarehouseId(userAdminWarehouseAvroModel.getUserAdminWarehouseId()))
                .withWarehouseId(new WarehouseId(userAdminWarehouseAvroModel.getWarehouseId()))
                .build();
    }
}
