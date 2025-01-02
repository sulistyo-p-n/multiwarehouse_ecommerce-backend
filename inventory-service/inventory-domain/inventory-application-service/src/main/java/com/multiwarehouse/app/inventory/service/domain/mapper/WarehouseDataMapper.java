package com.multiwarehouse.app.inventory.service.domain.mapper;

import com.multiwarehouse.app.inventory.service.domain.dto.WarehouseResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataMapper {
    public WarehouseResponse warehouseResponseFromWarehouse(Warehouse warehouse) {
        return WarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .code(warehouse.getCode())
                .name(warehouse.getName())
                .active(warehouse.isActive())
                .build();
    }
}
