package com.multiwarehouse.app.warehouse.service.domain.port.input.service;

import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehousesResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import jakarta.validation.Valid;

import java.util.UUID;

public interface WarehouseApplicationService {
    public CreateWarehouseResponse createWarehouse(UUID userId, @Valid CreateWarehouseCommand createWarehouseCommand);

    public UpdateWarehouseResponse updateWarehouse(UUID id, @Valid UpdateWarehouseCommand updateWarehouseCommand);

    public DeleteWarehouseResponse deleteWarehouse(UUID id);

    public GetWarehousesResponse getWarehouses();

    public GetWarehouseResponse getWarehouse(UUID id);
}
