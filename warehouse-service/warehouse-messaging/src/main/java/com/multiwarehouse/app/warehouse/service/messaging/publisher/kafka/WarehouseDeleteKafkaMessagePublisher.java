package com.multiwarehouse.app.warehouse.service.messaging.publisher.kafka;

import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseDeletedEvent;
import com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher.WarehouseDeletedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WarehouseDeleteKafkaMessagePublisher implements WarehouseDeletedMessagePublisher {
    @Override
    public void publish(WarehouseDeletedEvent domainEvent) {

    }
}
