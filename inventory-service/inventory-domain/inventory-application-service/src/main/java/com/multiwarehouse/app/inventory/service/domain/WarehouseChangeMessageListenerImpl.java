package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.Warehouse;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryCreatedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryDeletedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryUpdatedEvent;
import com.multiwarehouse.app.inventory.service.domain.ports.input.message.listener.WarehouseChangeMessageListener;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryCreatedMessagePublisher;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryDeletedMessagePublisher;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryUpdatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Collections;

@Slf4j
@Service
@Validated
public class WarehouseChangeMessageListenerImpl implements WarehouseChangeMessageListener {
    private final WarehouseHelper warehouseHelper;
    private final InventoryHelper inventoryHelper;
    private final InventoryDomainService inventoryDomainService;
    private final InventoryCreatedMessagePublisher inventoryCreatedMessagePublisher;
    private final InventoryUpdatedMessagePublisher inventoryUpdatedMessagePublisher;
    private final InventoryDeletedMessagePublisher inventoryDeletedMessagePublisher;

    public WarehouseChangeMessageListenerImpl(WarehouseHelper warehouseHelper, InventoryHelper inventoryHelper, InventoryDomainService inventoryDomainService, InventoryCreatedMessagePublisher inventoryCreatedMessagePublisher, InventoryUpdatedMessagePublisher inventoryUpdatedMessagePublisher, InventoryDeletedMessagePublisher inventoryDeletedMessagePublisher) {
        this.warehouseHelper = warehouseHelper;
        this.inventoryHelper = inventoryHelper;
        this.inventoryDomainService = inventoryDomainService;
        this.inventoryCreatedMessagePublisher = inventoryCreatedMessagePublisher;
        this.inventoryUpdatedMessagePublisher = inventoryUpdatedMessagePublisher;
        this.inventoryDeletedMessagePublisher = inventoryDeletedMessagePublisher;
    }

    @Override
    @Transactional
    public void warehouseCreated(Warehouse warehouse) {
        Warehouse warehouseSaved = warehouseHelper.saveWarehouse(warehouse);
        Inventory inventory = Inventory.builder()
                .withWarehouse(warehouseSaved)
                .withActive(warehouse.isActive())
                .build();
        InventoryCreatedEvent inventoryCreatedEvent = inventoryDomainService.validateAndInitializeInventory(inventory, inventoryCreatedMessagePublisher);
        Inventory inventorySaved = inventoryHelper.saveInventory(inventoryCreatedEvent.getInventory());
        log.info("Inventory is created with id: {}", inventorySaved.getId().getValue());
        inventoryCreatedMessagePublisher.publish(inventoryCreatedEvent);
    }

    @Override
    @Transactional
    public void warehouseUpdated(Warehouse warehouse) {
        Warehouse warehouseSaved = warehouseHelper.saveWarehouse(warehouse);
        Inventory inventory = inventoryHelper.findInventoryByWarehouseId(warehouseSaved.getId());
        InventoryUpdatedEvent inventoryUpdatedEvent = inventoryDomainService.validateAndSetInventory(inventory, warehouseSaved.isActive(), inventoryUpdatedMessagePublisher);
        Inventory inventorySaved = inventoryHelper.saveInventory(inventoryUpdatedEvent.getInventory());
        log.info("Inventory is updated with id: {}", inventorySaved.getId().getValue());
        inventoryUpdatedMessagePublisher.publish(inventoryUpdatedEvent);
    }

    @Override
    @Transactional
    public void warehouseSoftDeleted(Warehouse warehouse) {
        Inventory inventory = inventoryHelper.findInventoryByWarehouseId(warehouse.getId());
        InventoryDeletedEvent inventoryDeletedEvent = inventoryDomainService.removeInventory(inventory, inventoryDeletedMessagePublisher);
        inventoryDeletedEvent.setForceDelete(false);
        inventoryHelper.deleteInventory(inventory, false);
        warehouseHelper.deleteWarehouse(warehouse, false);
        inventoryDeletedMessagePublisher.publish(inventoryDeletedEvent);
    }

    @Override
    @Transactional
    public void warehouseHardDeleted(Warehouse warehouse) {
        Inventory inventory = inventoryHelper.findInventoryByWarehouseId(warehouse.getId());
        InventoryDeletedEvent inventoryDeletedEvent = inventoryDomainService.removeInventory(inventory, inventoryDeletedMessagePublisher);
        inventoryDeletedEvent.setForceDelete(true);
        inventoryHelper.deleteInventory(inventory, true);
        warehouseHelper.deleteWarehouse(warehouse, true);
        inventoryDeletedMessagePublisher.publish(inventoryDeletedEvent);
    }
}
