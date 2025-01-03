package com.multiwarehouse.app.authgateway.service.dataaccess.user.mapper;

import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.authgateway.service.dataaccess.user.entity.UserAdminWarehouseEntity;
import com.multiwarehouse.app.authgateway.service.dataaccess.user.entity.UserEntity;
import com.multiwarehouse.app.authgateway.service.domain.entity.User;
import com.multiwarehouse.app.authgateway.service.domain.entity.UserAdminWarehouse;
import org.springframework.stereotype.Component;

@Component
public class UserDataAccessMapper {
    public User userFromUserEntity(UserEntity userEntity) {
        UserId userId = new UserId(userEntity.getId());
        User user = User.builder()
                .withId(userId)
                .withUsername(userEntity.getUsername())
                .withEmail(userEntity.getEmail())
                .withPassword(userEntity.getPassword())
                .withActive(userEntity.getActive())
                .withRole(userEntity.getRole())
                .withUserAdminWarehouse(userAdminWarehouseFromUserAdminWarehouseEntity(userEntity.getUserAdminWarehouse()))
                .build();
        if (user.getUserAdminWarehouse() != null) user.getUserAdminWarehouse().setUserId(userId);
        return user;
    }

    private UserAdminWarehouse userAdminWarehouseFromUserAdminWarehouseEntity(UserAdminWarehouseEntity userAdminWarehouseEntity) {
        if (userAdminWarehouseEntity == null) return null;
        return UserAdminWarehouse.builder()
                .withWarehouseId(new WarehouseId(userAdminWarehouseEntity.getWarehouseId()))
                .build();
    }

    public UserEntity userEntityFromUser(User user) {
        UserEntity userEntity = UserEntity.builder()
                .id(user.getId().getValue())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.getActive())
                .role(user.getRole())
                .userAdminWarehouse(userAdminWarehouseEntityFromUserAdminWarehouse(user.getUserAdminWarehouse()))
                .build();
        if (userEntity.getUserAdminWarehouse() != null) userEntity.getUserAdminWarehouse().setUser(userEntity);
        return userEntity;
    }

    private UserAdminWarehouseEntity userAdminWarehouseEntityFromUserAdminWarehouse(UserAdminWarehouse userAdminWarehouse) {
        if (userAdminWarehouse == null) return null;
        return UserAdminWarehouseEntity.builder()
                .warehouseId(userAdminWarehouse.getWarehouseId().getValue())
                .build();
    }
}