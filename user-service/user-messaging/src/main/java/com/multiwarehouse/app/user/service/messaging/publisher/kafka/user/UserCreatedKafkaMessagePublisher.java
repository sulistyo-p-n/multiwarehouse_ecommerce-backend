package com.multiwarehouse.app.user.service.messaging.publisher.kafka.user;

import com.multiwarehouse.app.user.service.domain.event.UserCreatedEvent;
import com.multiwarehouse.app.user.service.domain.ports.output.message.publsher.user.UserCreatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserCreatedKafkaMessagePublisher implements UserCreatedMessagePublisher {
    @Override
    public void publish(UserCreatedEvent domainEvent) {

    }
}
