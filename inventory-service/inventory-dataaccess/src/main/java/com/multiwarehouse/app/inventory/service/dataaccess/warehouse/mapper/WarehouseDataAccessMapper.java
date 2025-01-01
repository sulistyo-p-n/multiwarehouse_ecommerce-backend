package com.multiwarehouse.app.inventory.service.dataaccess.warehouse.mapper;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.multiwarehouse.app.inventory.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataAccessMapper {
    public Warehouse warehouseFromWarehouseEntity(WarehouseEntity warehouseEntity) {
        return Warehouse.builder()
                .withId(new WarehouseId(warehouseEntity.getId()))
                .withCode(warehouseEntity.getCode())
                .withName(warehouseEntity.getName())
                .withActive(warehouseEntity.getActive())
                .withSoftDeleted(warehouseEntity.isSoftDeleted())
                .build();
    }

    public WarehouseEntity warehouseEntityFromWarehouse(Warehouse warehouse) {
        return WarehouseEntity.builder()
                .id(warehouse.getId().getValue())
                .code(warehouse.getCode())
                .name(warehouse.getName())
                .active(warehouse.isActive())
                .build();
    }
}
