package com.multiwarehouse.app.warehouse.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;

import java.time.ZonedDateTime;

public class WarehouseDeletedEvent extends WarehouseEvent {

    private final DomainEventPublisher<WarehouseDeletedEvent> warehouseDeletedEventDomainEventPublisher;

    public WarehouseDeletedEvent(Warehouse warehouse,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<WarehouseDeletedEvent> warehouseDeletedEventDomainEventPublisher) {
        super(warehouse, createdAt);
        this.warehouseDeletedEventDomainEventPublisher = warehouseDeletedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        warehouseDeletedEventDomainEventPublisher.publish(this);
    }
}
