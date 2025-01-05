package com.multiwarehouse.app.warehouse.service.messaging.mapper;

import com.multiwarehouse.app.kafka.warehouse.avro.model.ActionType;
import com.multiwarehouse.app.kafka.warehouse.avro.model.WarehouseChangeAvroModel;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseDeletedEvent;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseUpdatedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class WarehouseMessagingDataMapper {
    public WarehouseChangeAvroModel warehouseChangeAvroModelFromWarehouseCreatedEvent(WarehouseCreatedEvent warehouseCreatedEvent) {
        WarehouseChangeAvroModel warehouseChangeAvroModel = warehouseChangeAvroModelFromWarehouse(warehouseCreatedEvent.getWarehouse());
        warehouseChangeAvroModel.setActionType(ActionType.CREATED);
        warehouseChangeAvroModel.setCreatedAt(warehouseCreatedEvent.getCreatedAt().toInstant());
        return  warehouseChangeAvroModel;
    }

    public WarehouseChangeAvroModel warehouseChangeAvroModelFromWarehouseUpdatedEvent(WarehouseUpdatedEvent warehouseUpdatedEvent) {
        WarehouseChangeAvroModel warehouseChangeAvroModel = warehouseChangeAvroModelFromWarehouse(warehouseUpdatedEvent.getWarehouse());
        warehouseChangeAvroModel.setActionType(ActionType.UPDATED);
        warehouseChangeAvroModel.setCreatedAt(warehouseUpdatedEvent.getCreatedAt().toInstant());
        return  warehouseChangeAvroModel;
    }

    public WarehouseChangeAvroModel warehouseChangeAvroModelFromWarehouseDeletedEvent(WarehouseDeletedEvent warehouseDeletedEvent) {
        WarehouseChangeAvroModel warehouseChangeAvroModel = warehouseChangeAvroModelFromWarehouse(warehouseDeletedEvent.getWarehouse());
        warehouseChangeAvroModel.setActionType(warehouseDeletedEvent.isForceDeleted() ? ActionType.HARD_DELETED : ActionType.SOFT_DELETED);
        warehouseChangeAvroModel.setCreatedAt(warehouseDeletedEvent.getCreatedAt().toInstant());
        return  warehouseChangeAvroModel;
    }

    private WarehouseChangeAvroModel warehouseChangeAvroModelFromWarehouse(Warehouse warehouse) {
        return WarehouseChangeAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setWarehouseId(warehouse.getId().getValue())
                .setCode(warehouse.getCode())
                .setName(warehouse.getName())
                .setActive(warehouse.isActive())
                .setActionType(ActionType.DEFAULT)
                .setCreatedAt(Instant.now())
                .build();
    }
}