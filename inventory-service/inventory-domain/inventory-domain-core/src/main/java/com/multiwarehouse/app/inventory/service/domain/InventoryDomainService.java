package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.event.DomainEvent;
import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryCreatedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryDeletedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryUpdatedEvent;

import java.util.List;

public interface InventoryDomainService {
    InventoryCreatedEvent validateAndInitializeInventory(Inventory inventory, DomainEventPublisher<InventoryCreatedEvent> inventoryStockCreatedEventDomainEventPublisher);
    InventoryUpdatedEvent validateAndSetInventory(Inventory inventory, Boolean active, DomainEventPublisher<InventoryUpdatedEvent> inventoryStockUpdatedEventDomainEventPublisher);
    InventoryDeletedEvent removeInventory(Inventory inventory, DomainEventPublisher<InventoryDeletedEvent> inventoryStockDeletedEventDomainEventPublisher);
    InventoryStockChangedEvent addStock(Inventory inventory, Product product, int quantity, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher);
    InventoryStockChangedEvent reduceStock(Inventory inventory, Product product, int quantity, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher);
    StockMutation requestStockMutation(StockMutation stockMutation);
    StockMutation rejectStockMutation(StockMutation stockMutation);
    StockMutation approveStockMutation(StockMutation stockMutation);
    List<InventoryStockChangedEvent> transferStockMutation(StockMutation stockMutation, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher);
}
