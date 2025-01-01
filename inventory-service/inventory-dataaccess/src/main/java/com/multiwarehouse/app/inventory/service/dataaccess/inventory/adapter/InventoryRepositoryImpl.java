package com.multiwarehouse.app.inventory.service.dataaccess.inventory.adapter;

import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.InventoryEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.mapper.InventoryDataAccessMapper;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.repository.InventoryJpaRepository;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.ports.ouput.repository.InventoryRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class InventoryRepositoryImpl implements InventoryRepository {

    private final InventoryJpaRepository inventoryJpaRepository;
    private final InventoryDataAccessMapper inventoryDataAccessMapper;

    public InventoryRepositoryImpl(InventoryJpaRepository inventoryJpaRepository,
                                 InventoryDataAccessMapper inventoryDataAccessMapper) {
        this.inventoryJpaRepository = inventoryJpaRepository;
        this.inventoryDataAccessMapper = inventoryDataAccessMapper;
    }

    @Override
    public Optional<Inventory> findByWarehouseId(WarehouseId warehouseId) {
        return inventoryJpaRepository.findByWarehouseId(warehouseId.getValue()).map(inventoryDataAccessMapper::inventoryFromInventoryEntity);
    }

    @Override
    public Optional<Inventory> findById(InventoryId inventoryId) {
        return inventoryJpaRepository.findById(inventoryId.getValue()).map(inventoryDataAccessMapper::inventoryFromInventoryEntity);
    }

    @Override
    public Inventory save(Inventory inventory) {
        return inventoryDataAccessMapper.inventoryFromInventoryEntity(inventoryJpaRepository
                .save(inventoryDataAccessMapper.inventoryEntityFromInventory(inventory)));
    }

    @Override
    public void hardDelete(Inventory inventory) {
        inventoryJpaRepository.delete(inventoryDataAccessMapper.inventoryEntityFromInventory(inventory));
    }

    @Override
    public void softDelete(Inventory inventory) {
        InventoryEntity inventoryEntity = inventoryDataAccessMapper.inventoryEntityFromInventory(inventory);
        inventoryEntity.setDeletedAt(Instant.now());
        inventoryJpaRepository.save(inventoryEntity);
    }
}
