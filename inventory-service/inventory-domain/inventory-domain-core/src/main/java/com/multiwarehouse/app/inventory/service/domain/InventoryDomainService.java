package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;

import java.util.List;

public interface InventoryDomainService {
    void requestStockMutation(StockMutation stockMutation);
    void rejectStockMutation(StockMutation stockMutation);
    void acceptStockMutation(StockMutation stockMutation);
    List<InventoryStockChangedEvent> transferStockMutation(StockMutation stockMutation, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher);
    InventoryStockChangedEvent addStock(Inventory inventory, Product product, Integer quantity, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher);
    InventoryStockChangedEvent reduceStock(Inventory inventory, Product product, Integer quantity, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher);
}
