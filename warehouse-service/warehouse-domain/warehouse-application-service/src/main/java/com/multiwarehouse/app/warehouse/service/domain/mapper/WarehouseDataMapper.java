package com.multiwarehouse.app.warehouse.service.domain.mapper;

import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehousesResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarehouseDataMapper {

    public Warehouse createWarehouseCommandToWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return Warehouse.builder()
                .withName(createWarehouseCommand.getName())
                .withAddress(createWarehouseCommandToAddress(createWarehouseCommand))
                .build();
    }

    private Address createWarehouseCommandToAddress(CreateWarehouseCommand createWarehouseCommand) {
        return new Address(
                createWarehouseCommand.getAddressStreet(),
                createWarehouseCommand.getAddressCity(),
                createWarehouseCommand.getAddressPostalCode()
        );
    }

    public CreateWarehouseResponse warehouseToCreateWarehouseResponse(Warehouse warehouse) {
        return CreateWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .name(warehouse.getName())
                .addressStreet(warehouse.getAddress().getStreet())
                .addressCity(warehouse.getAddress().getCity())
                .addressPostalCode(warehouse.getAddress().getPostalCode())
                .build();
    }

    public Warehouse updateWarehouseCommandToWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        return Warehouse.builder()
                .withId(new WarehouseId(updateWarehouseCommand.getId()))
                .withName(updateWarehouseCommand.getName())
                .withAddress(updateWarehouseCommandToAddress(updateWarehouseCommand))
                .build();
    }

    private Address updateWarehouseCommandToAddress(UpdateWarehouseCommand updateWarehouseCommand) {
        return new Address(
                updateWarehouseCommand.getAddressStreet(),
                updateWarehouseCommand.getAddressCity(),
                updateWarehouseCommand.getAddressPostalCode()
        );
    }

    public UpdateWarehouseResponse warehouseToUpdateWarehouseResponse(Warehouse warehouse) {
        return UpdateWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .name(warehouse.getName())
                .addressStreet(warehouse.getAddress().getStreet())
                .addressCity(warehouse.getAddress().getCity())
                .addressPostalCode(warehouse.getAddress().getPostalCode())
                .build();
    }

    public GetWarehouseResponse warehouseToGetWarehouseResponse(Warehouse warehouse) {
        return GetWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .name(warehouse.getName())
                .addressStreet(warehouse.getAddress().getStreet())
                .addressCity(warehouse.getAddress().getCity())
                .addressPostalCode(warehouse.getAddress().getPostalCode())
                .build();
    }

    public GetWarehousesResponse warehousesToGetWarehousesResponse(List<Warehouse> warehouses) {
        return  GetWarehousesResponse.builder()
                .warehouses(
                        warehouses.stream().map(this::warehouseToGetWarehouseResponse).toList())
                .build();
    }

    public DeleteWarehouseResponse warehouseToDeleteWarehouseResponse(Warehouse warehouse) {
        return DeleteWarehouseResponse.builder()
                .id(warehouse.getId().getValue())
                .build();
    }

}
