package com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.mapper;

import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.entity.WarehouseAddressEntity;
import com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

@Component
public class WarehouseDataAccessMapper {
    public Warehouse warehouseFromWarehouseEntity(WarehouseEntity warehouseEntity) {
        return Warehouse.builder()
                .withId(new WarehouseId(warehouseEntity.getId()))
                .withCode(warehouseEntity.getCode())
                .withName(warehouseEntity.getName())
                .withDescription(warehouseEntity.getDescription())
                .withActive(warehouseEntity.getActive())
                .withSoftDeleted(warehouseEntity.isSoftDeleted())
                .withAddress(AddressFromWarehouseAddressEntity(warehouseEntity.getAddress()))
                .build();
    }

    private Address AddressFromWarehouseAddressEntity(WarehouseAddressEntity warehouseAddressEntity) {
        return new Address(
                warehouseAddressEntity.getStreet(),
                warehouseAddressEntity.getCity(),
                warehouseAddressEntity.getPostalCode(),
                warehouseAddressEntity.getLatitude(),
                warehouseAddressEntity.getLongitude());
    }

    public WarehouseEntity warehouseEntityFromWarehouse(Warehouse warehouse) {
        WarehouseEntity warehouseEntity = WarehouseEntity.builder()
                .id(warehouse.getId().getValue())
                .code(warehouse.getCode())
                .name(warehouse.getName())
                .description(warehouse.getDescription())
                .active(warehouse.isActive())
                .address(warehouseAddressEntityFromAddress(warehouse.getAddress()))
                .build();
        warehouseEntity.getAddress().setWarehouse(warehouseEntity);
        return warehouseEntity;
    }

    private WarehouseAddressEntity warehouseAddressEntityFromAddress(Address address) {
        return WarehouseAddressEntity.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .build();
    }
}
