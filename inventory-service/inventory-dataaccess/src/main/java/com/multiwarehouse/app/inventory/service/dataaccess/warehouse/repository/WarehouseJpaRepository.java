package com.multiwarehouse.app.inventory.service.dataaccess.warehouse.repository;

import com.multiwarehouse.app.dataaccess.repository.BaseJpaRepository;
import com.multiwarehouse.app.inventory.service.dataaccess.warehouse.entity.WarehouseEntity;

import java.util.UUID;

public interface WarehouseJpaRepository extends BaseJpaRepository<WarehouseEntity, UUID> {
}
