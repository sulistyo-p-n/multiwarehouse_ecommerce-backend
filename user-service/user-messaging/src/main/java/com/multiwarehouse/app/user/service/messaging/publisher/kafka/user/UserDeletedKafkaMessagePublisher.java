package com.multiwarehouse.app.user.service.messaging.publisher.kafka.user;

import com.multiwarehouse.app.user.service.domain.event.UserDeletedEvent;
import com.multiwarehouse.app.user.service.domain.ports.output.message.publsher.user.UserDeletedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserDeletedKafkaMessagePublisher implements UserDeletedMessagePublisher {
    @Override
    public void publish(UserDeletedEvent domainEvent) {

    }
}
