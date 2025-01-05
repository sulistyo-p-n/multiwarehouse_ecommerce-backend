package com.multiwarehouse.app.user.service.messaging.publisher.kafka.user;

import com.multiwarehouse.app.kafka.producer.KafkaMessageHelper;
import com.multiwarehouse.app.kafka.producer.service.KafkaProducer;
import com.multiwarehouse.app.kafka.user.avro.model.UserChangeAvroModel;
import com.multiwarehouse.app.user.service.domain.config.UserServiceConfigData;
import com.multiwarehouse.app.user.service.domain.event.UserCreatedEvent;
import com.multiwarehouse.app.user.service.domain.ports.output.message.publisher.user.UserCreatedMessagePublisher;
import com.multiwarehouse.app.user.service.messaging.mapper.UserMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserCreatedKafkaMessagePublisher implements UserCreatedMessagePublisher {
    private final UserMessagingDataMapper userMessagingDataMapper;
    private final UserServiceConfigData userServiceConfigData;
    private final KafkaProducer<String, UserChangeAvroModel> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;

    public UserCreatedKafkaMessagePublisher(UserMessagingDataMapper userMessagingDataMapper,
                                            UserServiceConfigData userServiceConfigData,
                                            KafkaProducer<String, UserChangeAvroModel> kafkaProducer,
                                            KafkaMessageHelper kafkaMessageHelper) {
        this.userMessagingDataMapper = userMessagingDataMapper;
        this.userServiceConfigData = userServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(UserCreatedEvent userCreatedEvent) {
        String userId = userCreatedEvent.getUser().getId().getValue().toString();
        log.info("Received UserCreatedEvent for userId: {}", userId);

        try {
            UserChangeAvroModel userChangeAvroModel = userMessagingDataMapper
                    .userChangeAvroModelFromUserCreatedEvent(userCreatedEvent);

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
