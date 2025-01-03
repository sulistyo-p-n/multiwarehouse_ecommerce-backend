package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehousesCommand;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.mapper.WarehouseDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class WarehousesGetCommandHandler {
    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseHelper warehouseHelper;

    public WarehousesGetCommandHandler(WarehouseDataMapper warehouseDataMapper, WarehouseHelper warehouseHelper) {
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseHelper = warehouseHelper;
    }

    @Transactional(readOnly = true)
    public List<GetWarehouseResponse> getWarehouses(GetWarehousesCommand getWarehousesCommand) {
        List<Warehouse> warehouses = warehouseHelper.findWarehouses(getWarehousesCommand);
        log.info("Warehouses is selected with total: {}", warehouses.size());
        return warehouses.stream().map(warehouseDataMapper::getWarehouseResponseFromWarehouse).collect(Collectors.toList());
    }
}
