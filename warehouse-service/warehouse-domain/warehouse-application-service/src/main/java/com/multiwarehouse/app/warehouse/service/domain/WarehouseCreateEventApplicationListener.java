package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher.WarehouseCreatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

//@Slf4j
//@Component
public class WarehouseCreateEventApplicationListener {
//    private final WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher;
//
//    public WarehouseCreateEventApplicationListener(WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher) {
//        this.warehouseCreatedMessagePublisher = warehouseCreatedMessagePublisher;
//    }
//
//    @TransactionalEventListener
//    void process(WarehouseCreatedEvent warehouseCreatedEvent) {
//        warehouseCreatedMessagePublisher.publish(warehouseCreatedEvent);
//    }
}
