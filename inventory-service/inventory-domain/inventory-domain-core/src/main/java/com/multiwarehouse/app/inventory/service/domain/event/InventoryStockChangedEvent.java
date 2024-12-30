package com.multiwarehouse.app.inventory.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;

import java.time.ZonedDateTime;

public class InventoryStockChangedEvent extends InventoryEvent {

    private final DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher;

    public InventoryStockChangedEvent(Inventory inventory,
                                      ZonedDateTime createdAt,
                                      DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher) {
        super(inventory, createdAt);
        this.inventoryStockChangedEventDomainEventPublisher = inventoryStockChangedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        inventoryStockChangedEventDomainEventPublisher.publish(this);
    }
}
