package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.entity.Inventory;
import com.multiwarehouse.app.product.service.domain.ports.input.message.listener.InventoryChangeMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class InventoryChangeMessageListenerImpl implements InventoryChangeMessageListener {
    private final InventoryHelper inventoryHelper;

    public InventoryChangeMessageListenerImpl(InventoryHelper inventoryHelper) {
        this.inventoryHelper = inventoryHelper;
    }

    @Override
    public void inventoryCreated(Inventory inventory) {
        inventoryHelper.saveInventory(inventory);
    }

    @Override
    public void inventoryUpdated(Inventory inventory) {
        inventoryHelper.saveInventory(inventory);
    }

    @Override
    public void inventorySoftDeleted(Inventory inventory) {
        inventoryHelper.deleteInventory(inventory, false);
    }

    @Override
    public void inventoryHardDeleted(Inventory inventory) {
        inventoryHelper.deleteInventory(inventory, true);
    }
}
