package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.constant.DomainConstants;
import com.multiwarehouse.app.domain.event.DomainEvent;
import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryCreatedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryDeletedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryUpdatedEvent;

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
    public InventoryUpdatedEvent validateAndSetInventory(Inventory inventory, Boolean active, DomainEventPublisher<InventoryUpdatedEvent> inventoryStockUpdatedEventDomainEventPublisher) {
        inventory.setActive(active);
        inventory.validate();
        return new InventoryUpdatedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockUpdatedEventDomainEventPublisher);
    }

    @Override
    public InventoryDeletedEvent removeInventory(Inventory inventory, DomainEventPublisher<InventoryDeletedEvent> inventoryStockDeletedEventDomainEventPublisher) {
        inventory.validate();
        return new InventoryDeletedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockDeletedEventDomainEventPublisher);
    }

    @Override
    public InventoryStockChangedEvent addStock(Inventory inventory, Product product, int quantity, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher) {
        inventory.addStock(product, quantity);
        inventory.validate();
        return new InventoryStockChangedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockChangedEventDomainEventPublisher);
    }

    @Override
    public InventoryStockChangedEvent reduceStock(Inventory inventory, Product product, int quantity, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher) {
        inventory.reduceStock(product, quantity);
        inventory.validate();
        return new InventoryStockChangedEvent(
                inventory,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                inventoryStockChangedEventDomainEventPublisher);
    }

    @Override
    public StockMutation requestStockMutation(StockMutation stockMutation) {
        stockMutation.validateInitialization();
        stockMutation.initialize();
        stockMutation.validate();
        stockMutation.request();
        return stockMutation;
    }

    @Override
    public StockMutation rejectStockMutation(StockMutation stockMutation) {
        stockMutation.reject();
        return stockMutation;
    }

    @Override
    public StockMutation approveStockMutation(StockMutation stockMutation) {
        stockMutation.validate();
        stockMutation.approve();
        return stockMutation;
    }

    @Override
    public List<InventoryStockChangedEvent> transferStockMutation(StockMutation stockMutation, DomainEventPublisher<InventoryStockChangedEvent> inventoryStockChangedEventDomainEventPublisher) {
        stockMutation.validate();
        stockMutation.validateStatusApproved();
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
