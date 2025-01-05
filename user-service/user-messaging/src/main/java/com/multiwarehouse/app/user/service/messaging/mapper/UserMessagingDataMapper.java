package com.multiwarehouse.app.user.service.messaging.mapper;

import com.multiwarehouse.app.kafka.user.avro.model.ActionType;
import com.multiwarehouse.app.kafka.user.avro.model.UserAdminWarehouseAvroModel;
import com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel;
import com.multiwarehouse.app.kafka.user.avro.model.UserRole;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.event.UserCreatedEvent;
import com.multiwarehouse.app.user.service.domain.event.UserDeletedEvent;
import com.multiwarehouse.app.user.service.domain.event.UserUpdatedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class UserMessagingDataMapper {
    public UserChangeAvroModel userChangeAvroModelFromUserCreatedEvent(UserCreatedEvent userCreatedEvent) {
        UserChangeAvroModel userChangeAvroModel = UserChangeAvroModelFromUser(userCreatedEvent.getUser());
        userChangeAvroModel.setActionType(ActionType.CREATED);
        userChangeAvroModel.setCreatedAt(userCreatedEvent.getCreatedAt().toInstant());
        return  userChangeAvroModel;
    }

    public UserChangeAvroModel userChangeAvroModelFromUserUpdatedEvent(UserUpdatedEvent userUpdatedEvent) {
        UserChangeAvroModel userChangeAvroModel = UserChangeAvroModelFromUser(userUpdatedEvent.getUser());
        userChangeAvroModel.setActionType(ActionType.UPDATED);
        userChangeAvroModel.setCreatedAt(userUpdatedEvent.getCreatedAt().toInstant());
        return  userChangeAvroModel;
    }

    public UserChangeAvroModel userChangeAvroModelFromUserDeletedEvent(UserDeletedEvent userDeletedEvent) {
        UserChangeAvroModel userChangeAvroModel = UserChangeAvroModelFromUser(userDeletedEvent.getUser());
        userChangeAvroModel.setActionType(userDeletedEvent.isForceDeleted() ? ActionType.HARD_DELETED : ActionType.SOFT_DELETED);
        userChangeAvroModel.setCreatedAt(userDeletedEvent.getCreatedAt().toInstant());
        return  userChangeAvroModel;
    }

    private UserChangeAvroModel UserChangeAvroModelFromUser(User user) {
        return UserChangeAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setUserId(user.getId().getValue())
                .setUsername(user.getUsername())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setRole(UserRole.valueOf(user.getRole().name()))
                .setActive(user.isActive())
                .setUserAdminWarehouse(
                        user.getUserAdminWarehouse() == null ? null : UserAdminWarehouseAvroModel.newBuilder()
                                .setUserAdminWarehouseId(user.getUserAdminWarehouse().getId().getValue())
                                .setWarehouseId(user.getUserAdminWarehouse().getWarehouseId().getValue())
                                .build()
                )
                .setActionType(ActionType.DEFAULT)
                .setCreatedAt(Instant.now())
                .build();
    }
}
