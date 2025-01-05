package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.Warehouse;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryCreatedEvent;
import com.multiwarehouse.app.inventory.service.domain.ports.input.message.listener.WarehouseChangeMessageListener;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryCreatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public WarehouseChangeMessageListenerImpl(WarehouseHelper warehouseHelper, InventoryHelper inventoryHelper, InventoryDomainService inventoryDomainService, InventoryCreatedMessagePublisher inventoryCreatedMessagePublisher) {
        this.warehouseHelper = warehouseHelper;
        this.inventoryHelper = inventoryHelper;
        this.inventoryDomainService = inventoryDomainService;
        this.inventoryCreatedMessagePublisher = inventoryCreatedMessagePublisher;
    }

    @Override
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
    public void warehouseUpdated(Warehouse warehouse) {
        Warehouse warehouseSaved = warehouseHelper.saveWarehouse(warehouse);
        Inventory inventory = inventoryHelper.findInventoryByWarehouseId(warehouse.getId());
        inventory.setActive(warehouse.isActive());
        Inventory inventorySaved = inventoryHelper.saveInventory(inventory);
        log.info("Inventory is updated with id: {}", inventorySaved.getId().getValue());
    }

    @Override
    public void warehouseSoftDeleted(Warehouse warehouse) {
        warehouseHelper.deleteWarehouse(warehouse, false);
    }

    @Override
    public void warehouseHardDeleted(Warehouse warehouse) {
        warehouseHelper.deleteWarehouse(warehouse, true);
    }
}
