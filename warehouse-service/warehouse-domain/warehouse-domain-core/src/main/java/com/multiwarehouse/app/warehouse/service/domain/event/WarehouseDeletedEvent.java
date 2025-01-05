package com.multiwarehouse.app.warehouse.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;

import java.time.ZonedDateTime;

public class WarehouseDeletedEvent extends WarehouseEvent {

    private final DomainEventPublisher<WarehouseDeletedEvent> warehouseDeletedEventDomainEventPublisher;

    private boolean forceDeleted;

    public WarehouseDeletedEvent(Warehouse warehouse,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<WarehouseDeletedEvent> warehouseDeletedEventDomainEventPublisher) {
        super(warehouse, createdAt);
        this.warehouseDeletedEventDomainEventPublisher = warehouseDeletedEventDomainEventPublisher;
    }

    public void setForceDeleted(boolean forceDeleted) {
        this.forceDeleted = forceDeleted;
    }

    public boolean isForceDeleted() {
        return forceDeleted;
    }

    @Override
    public void fire() {
        warehouseDeletedEventDomainEventPublisher.publish(this);
    }
}
