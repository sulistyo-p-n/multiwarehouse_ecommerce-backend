package com.multiwarehouse.app.inventory.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class InventoryUpdatedEvent extends InventoryEvent {

    private final DomainEventPublisher<InventoryUpdatedEvent> inventoryUpdatedEventDomainEventPublisher;

    public InventoryUpdatedEvent(Inventory inventory,
                                 ZonedDateTime createdAt,
                                 DomainEventPublisher<InventoryUpdatedEvent> inventoryUpdatedEventDomainEventPublisher) {
        super(inventory, createdAt);
        this.inventoryUpdatedEventDomainEventPublisher = inventoryUpdatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        inventoryUpdatedEventDomainEventPublisher.publish(this);
    }
}
