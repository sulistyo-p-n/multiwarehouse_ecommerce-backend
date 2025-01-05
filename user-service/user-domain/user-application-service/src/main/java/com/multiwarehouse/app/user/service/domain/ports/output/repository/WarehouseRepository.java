package com.multiwarehouse.app.user.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.user.service.domain.entity.Warehouse;

import java.util.Optional;

public interface WarehouseRepository {
    Optional<Warehouse> findById(WarehouseId warehouseId);
    Warehouse save(Warehouse warehouse);
    void hardDelete(Warehouse warehouse);
    void softDelete(Warehouse warehouse);
}
