package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher.WarehouseCreatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class WarehouseCreateCommandHandler {

    private final WarehouseDomainService warehouseDomainService;
    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseHelper warehouseHelper;
    private final WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher;

    public WarehouseCreateCommandHandler(WarehouseDomainService warehouseDomainService, WarehouseDataMapper warehouseDataMapper, WarehouseHelper warehouseHelper, WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher) {
        this.warehouseDomainService = warehouseDomainService;
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseHelper = warehouseHelper;
        this.warehouseCreatedMessagePublisher = warehouseCreatedMessagePublisher;
    }

    @Transactional
    public CreateWarehouseResponse createWarehouse(CreateWarehouseCommand createWarehouseCommand) {
        Warehouse warehouse = warehouseDataMapper.warehouseFromCreateWarehouseCommand(createWarehouseCommand);
        WarehouseCreatedEvent warehouseCreatedEvent = warehouseDomainService.validateAndInitiateWarehouse(warehouse, warehouseCreatedMessagePublisher);
        Warehouse warehouseSaved = warehouseHelper.saveWarehouse(warehouseCreatedEvent.getWarehouse());
        log.info("Warehouse is created with id: {}", warehouseSaved.getId().getValue());
        warehouseCreatedMessagePublisher.publish(warehouseCreatedEvent);
        return warehouseDataMapper.createWarehouseResponseFromWarehouse(warehouseSaved);
    }
}
