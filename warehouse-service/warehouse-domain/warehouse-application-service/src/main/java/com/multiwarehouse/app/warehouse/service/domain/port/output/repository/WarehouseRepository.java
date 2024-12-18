package com.multiwarehouse.app.warehouse.service.domain.port.output.repository;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;

import java.util.Optional;

public interface WarehouseRepository {

    Warehouse save(Warehouse warehouse);

    Optional<Warehouse> findById(WarehouseId warehouseId);
}
