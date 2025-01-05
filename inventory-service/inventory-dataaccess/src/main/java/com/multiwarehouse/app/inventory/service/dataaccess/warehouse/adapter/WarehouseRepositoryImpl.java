package com.multiwarehouse.app.inventory.service.dataaccess.warehouse.adapter;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.warehouse.mapper.WarehouseDataAccessMapper;
import com.multiwarehouse.app.inventory.service.dataaccess.warehouse.repository.WarehouseJpaRepository;
import com.multiwarehouse.app.inventory.service.domain.entity.Warehouse;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.WarehouseRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class WarehouseRepositoryImpl implements WarehouseRepository {

    private final WarehouseJpaRepository warehouseJpaRepository;
    private final WarehouseDataAccessMapper warehouseDataAccessMapper;

    public WarehouseRepositoryImpl(WarehouseJpaRepository warehouseJpaRepository,
                                   WarehouseDataAccessMapper warehouseDataAccessMapper) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.warehouseDataAccessMapper = warehouseDataAccessMapper;
    }

    @Override
    public Optional<Warehouse> findById(WarehouseId warehouseId) {
        return warehouseJpaRepository.findById(warehouseId.getValue()).map(warehouseDataAccessMapper::warehouseFromWarehouseEntity);
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseDataAccessMapper.warehouseFromWarehouseEntity(warehouseJpaRepository
                .save(warehouseDataAccessMapper.warehouseEntityFromWarehouse(warehouse)));
    }

    @Override
    public void hardDelete(Warehouse warehouse) {
        warehouseJpaRepository.delete(warehouseDataAccessMapper.warehouseEntityFromWarehouse(warehouse));
    }

    @Override
    public void softDelete(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = warehouseDataAccessMapper.warehouseEntityFromWarehouse(warehouse);
        warehouseEntity.setDeletedAt(Instant.now());
        warehouseJpaRepository.save(warehouseEntity);
    }
}

