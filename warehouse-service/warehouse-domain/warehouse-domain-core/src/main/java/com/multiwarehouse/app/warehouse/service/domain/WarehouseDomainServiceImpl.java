package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.domain.constant.DomainConstants;
import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.entity.User;
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
    public WarehouseCreatedEvent validationAndInitiateWarehouse(Warehouse warehouse, User user, DomainEventPublisher<WarehouseCreatedEvent> warehouseCreatedEventDomainEventPublisher) {
        user.validateUserIsSuperAdmin();
        warehouse.validationInitialWarehouse();
        warehouse.initializeWarehouse();

        return new WarehouseCreatedEvent(
                warehouse,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                warehouseCreatedEventDomainEventPublisher);
    }

    @Override
    public WarehouseUpdatedEvent validationToUpdateWarehouse(Warehouse warehouse, User user, DomainEventPublisher<WarehouseUpdatedEvent> warehouseUpdatedEventDomainEventPublisher) {
        user.validateUserCanManageWarehouse(warehouse.getId());

        return new WarehouseUpdatedEvent(
                warehouse,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                warehouseUpdatedEventDomainEventPublisher);
    }

    @Override
    public WarehouseDeletedEvent validationToDeleteWarehouse(Warehouse warehouse, User user, DomainEventPublisher<WarehouseDeletedEvent> warehouseDeletedEventDomainEventPublisher) {
        user.validateUserCanManageWarehouse(warehouse.getId());

        return new WarehouseDeletedEvent(
                warehouse,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                warehouseDeletedEventDomainEventPublisher);
    }

    @Override
    public void validationToGetWarehouse(WarehouseId warehouseId, User user) {
        user.validateUserCanManageWarehouse(warehouseId);
    }

    @Override
    public void validationToGetAllWarehouse(User user) {
        user.validateUserIsSuperAdmin();
    }
}
