package com.multiwarehouse.app.user.service.messaging.publisher.kafka.user;

import com.multiwarehouse.app.kafka.producer.KafkaMessageHelper;
import com.multiwarehouse.app.kafka.producer.service.KafkaProducer;
import com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel;
import com.multiwarehouse.app.user.service.domain.config.UserServiceConfigData;
import com.multiwarehouse.app.user.service.domain.event.UserDeletedEvent;
import com.multiwarehouse.app.user.service.domain.ports.output.message.publisher.user.UserDeletedMessagePublisher;
import com.multiwarehouse.app.user.service.messaging.mapper.UserMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDeletedKafkaMessagePublisher implements UserDeletedMessagePublisher {
    private final UserMessagingDataMapper userMessagingDataMapper;
    private final UserServiceConfigData userServiceConfigData;
    private final KafkaProducer<String, UserChangeAvroModel> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;

    public UserDeletedKafkaMessagePublisher(UserMessagingDataMapper userMessagingDataMapper,
                                            UserServiceConfigData userServiceConfigData,
                                            KafkaProducer<String, UserChangeAvroModel> kafkaProducer,
                                            KafkaMessageHelper kafkaMessageHelper) {
        this.userMessagingDataMapper = userMessagingDataMapper;
        this.userServiceConfigData = userServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(UserDeletedEvent userDeletedEvent) {
        String userId = userDeletedEvent.getUser().getId().getValue().toString();
        log.info("Received UserDeletedEvent for userId: {}", userId);

        try {
            UserChangeAvroModel userChangeAvroModel = userMessagingDataMapper
                    .userChangeAvroModelFromUserDeletedEvent(userDeletedEvent);

            kafkaProducer.send(userServiceConfigData.getUserChangeTopicName(),
                    userId,
                    userChangeAvroModel,
                    kafkaMessageHelper
                            .getKafkaCallback(userServiceConfigData.getUserChangeTopicName(),
                                    userChangeAvroModel,
                                    userId,
                                    "'UserChangeAvroModel"));

            log.info("UserChangeAvroModel sent to Kafka for userId: {}", userChangeAvroModel.getUserId());
        } catch (Exception e) {
            log.error("Error while sending UserChangeAvroModel message" +
                    " to kafka with userId: {}, error: {}", userId, e.getMessage());
        }
    }
}
