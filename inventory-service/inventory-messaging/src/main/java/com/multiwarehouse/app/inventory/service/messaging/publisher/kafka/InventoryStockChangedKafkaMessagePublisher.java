package com.multiwarehouse.app.inventory.service.messaging.publisher.kafka;

import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;
import com.multiwarehouse.app.inventory.service.domain.ports.ouput.message.publisher.InventoryStockChangedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryStockChangedKafkaMessagePublisher implements InventoryStockChangedMessagePublisher {
    @Override
    public void publish(InventoryStockChangedEvent domainEvent) {

    }
}
