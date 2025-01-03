package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehousesCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
public class WarehouseApplicationServiceImpl implements WarehouseApplicationService {

    private final WarehouseCreateCommandHandler WarehouseCreateCommandHandler;
    private final WarehouseDeleteCommandHandler WarehouseDeleteCommandHandler;
    private final WarehouseUpdateCommandHandler WarehouseUpdateCommandHandler;
    private final WarehouseGetCommandHandler WarehouseGetCommandHandler;
    private final WarehousesGetCommandHandler WarehousesGetCommandHandler;

    public WarehouseApplicationServiceImpl(WarehouseCreateCommandHandler WarehouseCreateCommandHandler, WarehouseDeleteCommandHandler WarehouseDeleteCommandHandler, WarehouseUpdateCommandHandler WarehouseUpdateCommandHandler, WarehouseGetCommandHandler WarehouseGetCommandHandler, WarehousesGetCommandHandler WarehousesGetCommandHandler) {
        this.WarehouseCreateCommandHandler = WarehouseCreateCommandHandler;
        this.WarehouseDeleteCommandHandler = WarehouseDeleteCommandHandler;
        this.WarehouseUpdateCommandHandler = WarehouseUpdateCommandHandler;
        this.WarehouseGetCommandHandler = WarehouseGetCommandHandler;
        this.WarehousesGetCommandHandler = WarehousesGetCommandHandler;
    }

    @Override
    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        return WarehouseCreateCommandHandler.createWarehouse(createWarehouseCommand);
    }

    @Override
    public UpdateWarehouseResponse updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        return WarehouseUpdateCommandHandler.updateWarehouse(updateWarehouseCommand);
    }

    @Override
    public DeleteWarehouseResponse deleteWarehouse(DeleteWarehouseCommand deleteWarehouseCommand) {
        return WarehouseDeleteCommandHandler.deleteWarehouse(deleteWarehouseCommand);
    }

    @Override
    public List<GetWarehouseResponse> getWarehouses(GetWarehousesCommand getWarehousesCommand) {
        return WarehousesGetCommandHandler.getWarehouses(getWarehousesCommand);
    }

    @Override
    public GetWarehouseResponse getWarehouse(GetWarehouseCommand getWarehouseCommand) {
        return WarehouseGetCommandHandler.getWarehouse(getWarehouseCommand);
    }
}
