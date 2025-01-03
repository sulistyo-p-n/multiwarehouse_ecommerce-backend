package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseDeletedEvent;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseUpdatedEvent;

public interface WarehouseDomainService {
    WarehouseCreatedEvent validateAndInitiateWarehouse(
            Warehouse warehouse,
            DomainEventPublisher<WarehouseCreatedEvent> warehouseCreatedEventDomainEventPublisher);

    WarehouseUpdatedEvent validateAndSetWarehouse(
            Warehouse warehouse,
            Warehouse newWarehouse,
            DomainEventPublisher<WarehouseUpdatedEvent> warehouseUpdatedEventDomainEventPublisher);

    WarehouseDeletedEvent validateAndRemoveWarehouse(
            Warehouse warehouse,
            DomainEventPublisher<WarehouseDeletedEvent> warehouseDeletedEventDomainEventPublisher);
}
