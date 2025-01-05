package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryNotFoundException;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.InventoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class InventoryHelper {
    private final InventoryRepository inventoryRepository;

    public InventoryHelper(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory findInventoryByWarehouseId(WarehouseId warehouseId) {
        Optional<Inventory> inventory = inventoryRepository.findByWarehouseId(warehouseId);
        if (inventory.isEmpty()) {
            log.warn("Couldn't find Inventory with warehouseId: {} ", warehouseId.getValue());
            throw new InventoryNotFoundException("Couldn't find Inventory with warehouseId: " + warehouseId.getValue());
        }
        return inventory.get();
    }

    public Inventory findInventoryById(InventoryId inventoryId) {
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
        if (inventory.isEmpty()) {
            log.warn("Couldn't find Inventory with id: {} ", inventoryId.getValue());
            throw new InventoryNotFoundException("Couldn't find Inventory with id: " + inventoryId.getValue());
        }
        return inventory.get();
    }

    public Inventory saveInventory(Inventory inventory) {
        Inventory inventorySaved = inventoryRepository.save(inventory);
        if (inventorySaved == null) {
            log.error("Couldn't save Inventory!");
            throw new InventoryDomainException("Cloud not save Inventory!");
        }
        log.info("Inventory is saved with id : {}", inventorySaved.getId().getValue());
        return inventorySaved;
    }

    public void deleteInventory(Inventory inventory, Boolean forceDelete) {
        try {
            if (forceDelete != null && forceDelete) {
                inventoryRepository.hardDelete(inventory);
            } else {
                inventoryRepository.softDelete(inventory);
            }
        } catch (Exception e) {
            throw new InventoryDomainException("Cloud not delete Inventory!");
        }
        log.info("Inventory is deleted with id : {}", inventory.getId().getValue());
    }
}
