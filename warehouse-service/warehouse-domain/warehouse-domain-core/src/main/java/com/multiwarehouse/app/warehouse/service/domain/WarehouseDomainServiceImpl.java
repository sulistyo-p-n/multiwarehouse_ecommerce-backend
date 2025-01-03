package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.domain.constant.DomainConstants;
import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseDeletedEvent;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseUpdatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class WarehouseDomainServiceImpl implements WarehouseDomainService {
    @Override
    public WarehouseCreatedEvent validateAndInitiateWarehouse(Warehouse warehouse, DomainEventPublisher<WarehouseCreatedEvent> warehouseCreatedEventDomainEventPublisher) {
        warehouse.validateInitialization();
        warehouse.initialize();
        warehouse.validate();
        return new WarehouseCreatedEvent(
                warehouse,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                warehouseCreatedEventDomainEventPublisher);
    }

    @Override
    public WarehouseUpdatedEvent validateAndSetWarehouse(Warehouse warehouse, Warehouse newWarehouse, DomainEventPublisher<WarehouseUpdatedEvent> warehouseUpdatedEventDomainEventPublisher) {
        warehouse.validateId();
        warehouse.setCode(newWarehouse.getCode());
        warehouse.setName(newWarehouse.getName());
        warehouse.setDescription(newWarehouse.getDescription());
        warehouse.setAddress(newWarehouse.getAddress());
        warehouse.setActive(newWarehouse.isActive());
        warehouse.validate();
        return new WarehouseUpdatedEvent(
                warehouse,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                warehouseUpdatedEventDomainEventPublisher);
    }

    @Override
    public WarehouseDeletedEvent validateAndRemoveWarehouse(Warehouse warehouse, DomainEventPublisher<WarehouseDeletedEvent> warehouseDeletedEventDomainEventPublisher) {
        warehouse.validate();
        return new WarehouseDeletedEvent(
                warehouse,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                warehouseDeletedEventDomainEventPublisher);
    }
}
