package com.multiwarehouse.app.warehouse.service.domain.port.input.service;

import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehousesCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface WarehouseApplicationService {
    public CreateWarehouseResponse createWarehouse(@Valid CreateWarehouseCommand createWarehouseCommand);

    public UpdateWarehouseResponse updateWarehouse(@Valid UpdateWarehouseCommand updateWarehouseCommand);

    public DeleteWarehouseResponse deleteWarehouse(@Valid DeleteWarehouseCommand deleteWarehouseCommand);

    public List<GetWarehouseResponse> getWarehouses(@Valid GetWarehousesCommand getWarehousesCommand);

    public GetWarehouseResponse getWarehouse(@Valid GetWarehouseCommand getWarehouseCommand);
}
