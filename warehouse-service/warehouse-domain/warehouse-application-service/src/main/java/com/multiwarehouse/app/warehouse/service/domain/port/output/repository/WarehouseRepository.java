package com.multiwarehouse.app.warehouse.service.domain.port.output.repository;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository {
    List<Warehouse> findAll();
    List<Warehouse> findByCriteria(Boolean withInactive, Boolean withTrashed, String search);
    Optional<Warehouse> findById(WarehouseId warehouseId);
    Warehouse save(Warehouse product);
    void hardDelete(Warehouse product);
    void softDelete(Warehouse product);
}
