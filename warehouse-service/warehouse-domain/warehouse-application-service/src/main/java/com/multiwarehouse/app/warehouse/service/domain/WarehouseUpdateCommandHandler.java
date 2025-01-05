package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseUpdatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher.WarehouseUpdatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class WarehouseUpdateCommandHandler {
    private final WarehouseDomainService warehouseDomainService;
    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseHelper warehouseHelper;
    private final WarehouseUpdatedMessagePublisher warehouseUpdatedMessagePublisher;

    public WarehouseUpdateCommandHandler(WarehouseDomainService warehouseDomainService, WarehouseDataMapper warehouseDataMapper, WarehouseHelper warehouseHelper, WarehouseUpdatedMessagePublisher warehouseUpdatedMessagePublisher) {
        this.warehouseDomainService = warehouseDomainService;
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseHelper = warehouseHelper;
        this.warehouseUpdatedMessagePublisher = warehouseUpdatedMessagePublisher;
    }

    @Transactional
    public UpdateWarehouseResponse updateWarehouse(UpdateWarehouseCommand updateWarehouseCommand) {
        WarehouseId warehouseId = new WarehouseId(updateWarehouseCommand.getId());
        Warehouse warehouse = warehouseHelper.findWarehouseById(warehouseId);
        Warehouse newWarehouse = warehouseDataMapper.warehouseFromUpdateWarehouseCommand(updateWarehouseCommand);
        WarehouseUpdatedEvent warehouseUpdatedEvent = warehouseDomainService.validateAndSetWarehouse(warehouse, newWarehouse, warehouseUpdatedMessagePublisher);
        Warehouse warehouseSaved = warehouseHelper.saveWarehouse(warehouseUpdatedEvent.getWarehouse());
        log.info("Warehouse is updated with id: {}", warehouseSaved.getId().getValue());
        warehouseUpdatedMessagePublisher.publish(warehouseUpdatedEvent);
        return warehouseDataMapper.updateWarehouseResponseFromWarehouse(warehouseSaved);
    }
}
