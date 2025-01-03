package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseDeletedEvent;
import com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher.WarehouseDeletedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class WarehouseDeleteCommandHandler {
    private final WarehouseDomainService warehouseDomainService;
    private final WarehouseHelper warehouseHelper;
    private final WarehouseDeletedMessagePublisher warehouseDeletedMessagePublisher;

    public WarehouseDeleteCommandHandler(WarehouseDomainService warehouseDomainService, WarehouseHelper warehouseHelper, WarehouseDeletedMessagePublisher warehouseDeletedMessagePublisher) {
        this.warehouseDomainService = warehouseDomainService;
        this.warehouseHelper = warehouseHelper;
        this.warehouseDeletedMessagePublisher = warehouseDeletedMessagePublisher;
    }

    @Transactional
    public DeleteWarehouseResponse deleteWarehouse(DeleteWarehouseCommand deleteWarehouseCommand) {
        WarehouseId warehouseId = new WarehouseId(deleteWarehouseCommand.getId());
        Warehouse warehouse = warehouseHelper.findWarehouseById(warehouseId);
        WarehouseDeletedEvent warehouseDeletedEvent = warehouseDomainService.validateAndRemoveWarehouse(warehouse, warehouseDeletedMessagePublisher);
        warehouseHelper.deleteWarehouse(warehouseDeletedEvent.getWarehouse(),deleteWarehouseCommand.getForceDelete());
        log.info("Warehouse is deleted with id: {}", warehouseDeletedEvent.getWarehouse().getId().getValue());
        return  DeleteWarehouseResponse.builder().id(warehouseDeletedEvent.getWarehouse().getId().getValue()).build();
    }
}
