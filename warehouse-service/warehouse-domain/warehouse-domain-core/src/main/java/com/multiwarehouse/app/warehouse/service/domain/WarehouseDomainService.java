package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.entity.User;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseDeletedEvent;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseUpdatedEvent;

public interface WarehouseDomainService {
    WarehouseCreatedEvent validationAndInitiateWarehouse(
            Warehouse warehouse,
            User user,
            DomainEventPublisher<WarehouseCreatedEvent> warehouseCreatedEventDomainEventPublisher);

    WarehouseUpdatedEvent validationToUpdateWarehouse(
            Warehouse warehouse,
            User user,
            DomainEventPublisher<WarehouseUpdatedEvent> warehouseUpdatedEventDomainEventPublisher);

    WarehouseDeletedEvent validationToDeleteWarehouse(
            Warehouse warehouse,
            User user,
            DomainEventPublisher<WarehouseDeletedEvent> warehouseDeletedEventDomainEventPublisher);

    void validationToGetWarehouse(WarehouseId warehouseId, User user);

    void validationToGetAllWarehouse(User user);
}
