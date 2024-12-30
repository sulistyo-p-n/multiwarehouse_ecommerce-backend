package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;

import java.util.List;

public interface InventoryDomainService {
    List<InventoryStockChangedEvent> transferBetweenWarehouse(Inventory sourceInventory, Inventory targetInventory, Product product, Integer quantity, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher);
}
