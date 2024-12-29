package com.multiwarehouse.app.user.service.messaging.publisher.kafka.user;

import com.multiwarehouse.app.user.service.domain.event.UserUpdatedEvent;
import com.multiwarehouse.app.user.service.domain.ports.output.message.publsher.user.UserUpdatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserUpdatedKafkaMessagePublisher implements UserUpdatedMessagePublisher {
    @Override
    public void publish(UserUpdatedEvent domainEvent) {

    }
}
