package com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.mapper;

import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataAccessMapper {
    public Warehouse warehouseEntityToWarehouse(WarehouseEntity warehouseEntity) {
        return Warehouse.builder()
                .withId(new WarehouseId(warehouseEntity.getId()))
                .withName(warehouseEntity.getName())
                .withAddress(warehouseEntityToAddress(warehouseEntity))
                .build();
    }

    private Address warehouseEntityToAddress(WarehouseEntity warehouseEntity) {
        return new Address(
                warehouseEntity.getAddressStreet(),
                warehouseEntity.getAddressCity(),
                warehouseEntity.getAddressPostalCode());
    }

    public WarehouseEntity warehouseToWarehouseEntity(Warehouse warehouse) {
        return WarehouseEntity.builder()
                .id(warehouse.getId().getValue())
                .name(warehouse.getName())
                .addressStreet(warehouse.getAddress().getStreet())
                .addressCity(warehouse.getAddress().getCity())
                .addressPostalCode(warehouse.getAddress().getPostalCode())
                .build();
    }
}
