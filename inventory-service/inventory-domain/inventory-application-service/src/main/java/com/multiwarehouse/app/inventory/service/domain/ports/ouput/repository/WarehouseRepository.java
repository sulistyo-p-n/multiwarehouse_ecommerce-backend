package com.multiwarehouse.app.inventory.service.domain.ports.ouput.repository;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.entity.Warehouse;

import java.time.Instant;
import java.util.Optional;

public interface WarehouseRepository {
    Optional<Warehouse> findById(WarehouseId warehouseId);
    Warehouse save(Warehouse warehouse);
    void hardDelete(Warehouse warehouse);
    void softDelete(Warehouse warehouse);
}
