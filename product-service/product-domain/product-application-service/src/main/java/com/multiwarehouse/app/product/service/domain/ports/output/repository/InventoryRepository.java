package com.multiwarehouse.app.product.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.product.service.domain.entity.Inventory;

import java.util.Optional;

public interface InventoryRepository {
    Optional<Inventory> findById(InventoryId inventoryId);
    Inventory save(Inventory inventory);
    void hardDelete(Inventory inventory);
    void softDelete(Inventory inventory);
}
