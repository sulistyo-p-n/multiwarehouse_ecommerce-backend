package com.multiwarehouse.app.inventory.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class InventoryCreatedEvent extends InventoryEvent {

    private final DomainEventPublisher<InventoryCreatedEvent> inventoryStockCreatedEventDomainEventPublisher;

    public InventoryCreatedEvent(Inventory inventory,
                                      ZonedDateTime createdAt,
                                      DomainEventPublisher<InventoryCreatedEvent> inventoryStockCreatedEventDomainEventPublisher) {
        super(inventory, createdAt);
        this.inventoryStockCreatedEventDomainEventPublisher = inventoryStockCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        inventoryStockCreatedEventDomainEventPublisher.publish(this);
    }
}
