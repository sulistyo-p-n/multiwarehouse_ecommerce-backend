package com.multiwarehouse.app.product.service.domain.ports.input.message.listener;

import com.multiwarehouse.app.product.service.domain.entity.Inventory;

public interface InventoryChangeMessageListener {
    void inventoryCreated(Inventory inventory);
    void inventoryUpdated(Inventory inventory);
    void inventorySoftDeleted(Inventory inventory);
    void inventoryHardDeleted(Inventory inventory);
}
