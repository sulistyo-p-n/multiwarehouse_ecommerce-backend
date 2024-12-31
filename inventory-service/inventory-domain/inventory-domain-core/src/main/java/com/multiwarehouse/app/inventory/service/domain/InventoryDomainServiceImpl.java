package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.constant.DomainConstants;
import com.multiwarehouse.app.domain.event.DomainEvent;
import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryCreatedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class InventoryDomainServiceImpl implements InventoryDomainService {
    @Override
    public InventoryCreatedEvent validateAndInitializeInventory(Inventory inventory, DomainEventPublisher<InventoryCreatedEvent> inventoryStockCreatedEventDomainEventPublisher) {
        inventory.validateInitialization();
        inventory.initialize();
        inventory.validate();
        return new InventoryCreatedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockCreatedEventDomainEventPublisher);
    }

    @Override
    public InventoryStockChangedEvent addStock(Inventory inventory, Product product, int quantity, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher) {
        validateQuantity(quantity);
        inventory.addStock(product, quantity);
        inventory.validate();
        return new InventoryStockChangedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockChangedEventDomainEventPublisher);
    }

    @Override
    public InventoryStockChangedEvent reduceStock(Inventory inventory, Product product, int quantity, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher) {
        validateQuantity(quantity);
        inventory.reduceStock(product, quantity);
        inventory.validate();
        return new InventoryStockChangedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockChangedEventDomainEventPublisher);
    }

    private void validateQuantity(int quantity) {
        if (quantity == 0) throw new InventoryDomainException("Stock Quantity must be greater than zero!");
    }

    @Override
    public void requestStockMutation(StockMutation stockMutation) {
        stockMutation.validateQuantity();
        stockMutation.validateSourceInventoryAvailableStock();
        stockMutation.validateInventory();
        stockMutation.request();
    }

    @Override
    public void rejectStockMutation(StockMutation stockMutation) {
        stockMutation.reject();
    }

    @Override
    public void approveStockMutation(StockMutation stockMutation) {
        stockMutation.validateQuantity();
        stockMutation.validateSourceInventoryAvailableStock();
        stockMutation.validateInventory();
        stockMutation.approve();
    }

    @Override
    public List<InventoryStockChangedEvent> transferStockMutation(StockMutation stockMutation, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher) {
        stockMutation.validateStatus();
        stockMutation.validateQuantity();
        stockMutation.validateSourceInventoryAvailableStock();
        stockMutation.transferStock();
        stockMutation.validateInventory();

        List<InventoryStockChangedEvent> inventoryStockChangedEvents = new ArrayList<>();

        inventoryStockChangedEvents.add(new InventoryStockChangedEvent(
                stockMutation.getSourceInventory(),
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockChangedEventDomainEventPublisher));

        inventoryStockChangedEvents.add(new InventoryStockChangedEvent(
                stockMutation.getTargetInventory(),
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockChangedEventDomainEventPublisher));

        return inventoryStockChangedEvents;
    }
}
