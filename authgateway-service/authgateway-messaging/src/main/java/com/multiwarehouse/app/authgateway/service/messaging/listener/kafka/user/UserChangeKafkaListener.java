package com.multiwarehouse.app.authgateway.service.messaging.listener.kafka.user;

import com.multiwarehouse.app.authgateway.service.domain.port.input.message.listener.UserChangeMessageListener;
import com.multiwarehouse.app.authgateway.service.messaging.mapper.UserMessagingDataMapper;
import com.multiwarehouse.app.kafka.consumer.KafkaConsumer;
import com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel;
import com.multiwarehouse.app.kafka.user.avro.model.ActionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserChangeKafkaListener implements KafkaConsumer<UserChangeAvroModel> {
    private final UserChangeMessageListener userChangeMessageListener;
    private final UserMessagingDataMapper userMessagingDataMapper;

    public UserChangeKafkaListener(UserChangeMessageListener userChangeMessageListener, UserMessagingDataMapper userMessagingDataMapper) {
        this.userChangeMessageListener = userChangeMessageListener;
        this.userMessagingDataMapper = userMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.user-change-consumer-group-id}", topics = "${authgateway-service.user-change-topic-name}")
    public void receive(
            @Payload List<UserChangeAvroModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(userChangeAvroModel -> {
            log.info("UserChangeAvroModel receive with Id:{}", userChangeAvroModel.getId());
            if (userChangeAvroModel.getActionType() == ActionType.CREATED) {
                log.info("Processing User Create with Id:{}", userChangeAvroModel.getUserId());
                userChangeMessageListener.userCreated(userMessagingDataMapper.userFromUserChangeAvroModel(userChangeAvroModel));
            } else if (userChangeAvroModel.getActionType() == ActionType.UPDATED) {
                log.info("Processing User Update with Id:{}", userChangeAvroModel.getUserId());
                userChangeMessageListener.userUpdated(userMessagingDataMapper.userFromUserChangeAvroModel(userChangeAvroModel));
            } else if (userChangeAvroModel.getActionType() == ActionType.SOFT_DELETED) {
                log.info("Processing User Soft Delete with Id:{}", userChangeAvroModel.getUserId());
                userChangeMessageListener.userSoftDeleted(userMessagingDataMapper.userFromUserChangeAvroModel(userChangeAvroModel));
            } else if (userChangeAvroModel.getActionType() == ActionType.HARD_DELETED) {
                log.info("Processing User Hard Delete with Id:{}", userChangeAvroModel.getUserId());
                userChangeMessageListener.userHardDeleted(userMessagingDataMapper.userFromUserChangeAvroModel(userChangeAvroModel));
            }
        });
    }
}
