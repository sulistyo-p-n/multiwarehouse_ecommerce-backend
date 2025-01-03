package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.mapper.WarehouseDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class WarehouseGetCommandHandler {
    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseHelper warehouseHelper;

    public WarehouseGetCommandHandler(WarehouseDataMapper warehouseDataMapper, WarehouseHelper warehouseHelper) {
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseHelper = warehouseHelper;
    }

    @Transactional(readOnly = true)
    public GetWarehouseResponse getWarehouse(GetWarehouseCommand getWarehouseCommand) {
        WarehouseId warehouseId = new WarehouseId(getWarehouseCommand.getId());
        Warehouse warehouse = warehouseHelper.findWarehouseById(warehouseId);
        log.info("Warehouse is selected with id: {}", warehouse.getId().getValue());
        return warehouseDataMapper.getWarehouseResponseFromWarehouse(warehouse);
    }
}
