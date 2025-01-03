package com.multiwarehouse.app.warehouse.service.messaging.publisher.kafka;

import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseUpdatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher.WarehouseUpdatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WarehouseUpdateKafkaMessagePublisher implements WarehouseUpdatedMessagePublisher {
    @Override
    public void publish(WarehouseUpdatedEvent domainEvent) {

    }
}
