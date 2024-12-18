package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehousesResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
public class WarehouseApplicationServiceImpl implements WarehouseApplicationService {

    private final WarehouseCreateCommandHandler warehouseCreateCommandHandler;

    public WarehouseApplicationServiceImpl(WarehouseCreateCommandHandler warehouseCreateCommandHandler) {
        this.warehouseCreateCommandHandler = warehouseCreateCommandHandler;
    }

    @Override
    public CreateWarehouseResponse createWarehouse(UUID userId, CreateWarehouseCommand createWarehouseCommand) {
        return warehouseCreateCommandHandler.createWarehouse(userId, createWarehouseCommand);
    }

    @Override
    public UpdateWarehouseResponse updateWarehouse(UUID id, UpdateWarehouseCommand updateWarehouseCommand) {
        return null;
    }

    @Override
    public DeleteWarehouseResponse deleteWarehouse(UUID id) {
        return null;
    }

    @Override
    public GetWarehousesResponse getWarehouses() {
        return null;
    }

    @Override
    public GetWarehouseResponse getWarehouse(UUID id) {
        return null;
    }
}
