package com.multiwarehouse.app.warehouse.service.dataaccess.user.mapper;

import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.dataaccess.user.entity.UserEntity;
import com.multiwarehouse.app.warehouse.service.domain.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDataAccessMapper {
    public User userEntityToUser(UserEntity userEntity) {
        return User.builder()
                .withId(new UserId(userEntity.getId()))
                .withRole(userEntity.getRole())
                .withWarehouseId(new WarehouseId(userEntity.getWarehouseId()))
                .build();
    }
}
