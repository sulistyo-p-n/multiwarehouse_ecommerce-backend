package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher.WarehouseCreatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class WarehouseCreateCommandHandler {

    private final WarehouseCreateHelper warehouseCreateHelper;
    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher;

    public WarehouseCreateCommandHandler(WarehouseCreateHelper warehouseCreateHelper, WarehouseDataMapper warehouseDataMapper, WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher) {
        this.warehouseCreateHelper = warehouseCreateHelper;
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseCreatedMessagePublisher = warehouseCreatedMessagePublisher;
    }

    public CreateWarehouseResponse createWarehouse(UUID userId, CreateWarehouseCommand createWarehouseCommand) {
        WarehouseCreatedEvent warehouseCreatedEvent = warehouseCreateHelper.persistWarehouse(userId, createWarehouseCommand);
        log.info("Warehouse is created with id: {}", warehouseCreatedEvent.getWarehouse().getId().getValue());
        warehouseCreatedMessagePublisher.publish(warehouseCreatedEvent);
        return warehouseDataMapper.warehouseToCreateWarehouseResponse(warehouseCreatedEvent.getWarehouse());
    }
}
