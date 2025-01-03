package com.multiwarehouse.app.warehouse.service.domain.mapper;

import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseAddressCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseAddressResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseAddressCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarehouseDataMapper {

    public Warehouse warehouseFromCreateWarehouseCommand(CreateWarehouseCommand createWarehouseCommand) {
        return Warehouse.builder()
                .withCode(createWarehouseCommand.getCode())
                .withName(createWarehouseCommand.getName())
                .withDescription(createWarehouseCommand.getDescription())
                .withAddress(addressFromCreateWarehouseCommand(createWarehouseCommand.getAddress()))
                .withActive(createWarehouseCommand.getActive())
                .build();
    }

    private Address addressFromCreateWarehouseCommand(CreateWarehouseAddressCommand createWarehouseAddressCommand) {
        return new Address(
                createWarehouseAddressCommand.getStreet(),
                createWarehouseAddressCommand.getCity(),
                createWarehouseAddressCommand.getPostalCode(),
                createWarehouseAddressCommand.getLatitude(),
                createWarehouseAddressCommand.getLongitude()
        );
    }

    public CreateWarehouseResponse createWarehouseResponseFromWarehouse(Warehouse warehouse) {
        return CreateWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .build();
    }

    public Warehouse warehouseFromUpdateWarehouseCommand(UpdateWarehouseCommand updateWarehouseCommand) {
        return Warehouse.builder()
                .withId(new WarehouseId(updateWarehouseCommand.getId()))
                .withCode(updateWarehouseCommand.getCode())
                .withName(updateWarehouseCommand.getName())
                .withDescription(updateWarehouseCommand.getDescription())
                .withAddress(addressFromUpdateWarehouseCommand(updateWarehouseCommand.getAddress()))
                .withActive(updateWarehouseCommand.getActive())
                .build();
    }

    private Address addressFromUpdateWarehouseCommand(UpdateWarehouseAddressCommand updateWarehouseAddressCommand) {
        return updateWarehouseAddressCommand == null ? null : new Address(
                updateWarehouseAddressCommand.getStreet(),
                updateWarehouseAddressCommand.getCity(),
                updateWarehouseAddressCommand.getPostalCode(),
                updateWarehouseAddressCommand.getLatitude(),
                updateWarehouseAddressCommand.getLongitude()
        );
    }

    public UpdateWarehouseResponse updateWarehouseResponseFromWarehouse(Warehouse warehouse) {
        return UpdateWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .build();
    }

    public GetWarehouseResponse getWarehouseResponseFromWarehouse(Warehouse warehouse) {
        return GetWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .code(warehouse.getCode())
                .name(warehouse.getName())
                .description(warehouse.getDescription())
                .address(getWarehouseAddressResponseFromAddress(warehouse.getAddress()))
                .active(warehouse.isActive())
                .softDeleted(warehouse.isSoftDeleted())
                .build();
    }

    public GetWarehouseAddressResponse getWarehouseAddressResponseFromAddress(Address address) {
        return GetWarehouseAddressResponse.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .latitude(address.getLatitude())
                .longitude(address.getLongitude())
                .build();
    }

    public DeleteWarehouseResponse deleteWarehouseResponseFromWarehouse(Warehouse warehouse) {
        return DeleteWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .build();
    }

}
