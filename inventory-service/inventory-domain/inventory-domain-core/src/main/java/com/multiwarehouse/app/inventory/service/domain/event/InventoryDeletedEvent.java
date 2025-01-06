package com.multiwarehouse.app.inventory.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class InventoryDeletedEvent extends InventoryEvent {

    private final DomainEventPublisher<InventoryDeletedEvent> inventoryDeletedEventDomainEventPublisher;

    private boolean forceDelete;

    public InventoryDeletedEvent(Inventory inventory,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<InventoryDeletedEvent> inventoryDeletedEventDomainEventPublisher) {
        super(inventory, createdAt);
        this.inventoryDeletedEventDomainEventPublisher = inventoryDeletedEventDomainEventPublisher;
    }

    public boolean isForceDelete() {
        return forceDelete;
    }

    public void setForceDelete(boolean forceDelete) {
        this.forceDelete = forceDelete;
    }

    @Override
    public void fire() {
        inventoryDeletedEventDomainEventPublisher.publish(this);
    }
}
