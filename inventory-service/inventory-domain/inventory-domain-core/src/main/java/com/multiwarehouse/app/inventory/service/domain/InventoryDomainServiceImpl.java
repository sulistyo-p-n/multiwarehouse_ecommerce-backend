package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.constant.DomainConstants;
import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryDomainServiceImpl implements InventoryDomainService {
    @Override
    public List<InventoryStockChangedEvent> transferBetweenWarehouse(Inventory sourceInventory, Inventory targetInventory, Product product, Integer quantity, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher) {
        sourceInventory.transferStock(targetInventory, product, quantity);
        List<InventoryStockChangedEvent> inventoryStockChangedEvents = new ArrayList<>();

        inventoryStockChangedEvents.add(new InventoryStockChangedEvent(
                sourceInventory,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockChangedEventDomainEventPublisher));

        inventoryStockChangedEvents.add(new InventoryStockChangedEvent(
                targetInventory,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockChangedEventDomainEventPublisher));

        return inventoryStockChangedEvents;
    }
}
