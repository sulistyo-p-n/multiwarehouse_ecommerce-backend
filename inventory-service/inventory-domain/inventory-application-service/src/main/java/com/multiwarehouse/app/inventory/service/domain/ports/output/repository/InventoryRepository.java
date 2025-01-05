package com.multiwarehouse.app.inventory.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;

import java.util.Optional;

public interface InventoryRepository {
    Optional<Inventory> findByWarehouseId(WarehouseId warehouseId);
    Optional<Inventory> findById(InventoryId inventoryId);
    Inventory save(Inventory inventory);
    void hardDelete(Inventory inventory);
    void softDelete(Inventory inventory);
}
