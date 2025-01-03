package com.multiwarehouse.app.inventory.service.messaging.publisher.kafka;

import com.multiwarehouse.app.inventory.service.domain.event.InventoryCreatedEvent;
import com.multiwarehouse.app.inventory.service.domain.ports.ouput.message.publisher.InventoryCreatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryCreatedKafkaMessagePublisher implements InventoryCreatedMessagePublisher {
    @Override
    public void publish(InventoryCreatedEvent domainEvent) {

    }
}
