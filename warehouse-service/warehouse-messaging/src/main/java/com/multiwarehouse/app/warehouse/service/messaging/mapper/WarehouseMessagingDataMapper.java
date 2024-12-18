package com.multiwarehouse.app.warehouse.service.messaging.mapper;


import com.multiwarehouse.app.kafka.order.avro.model.WarehouseCreateAvroModel;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WarehouseMessagingDataMapper {
    public WarehouseCreateAvroModel warehouseCreatedEventToWarehouseCreateAvroModel(WarehouseCreatedEvent warehouseCreatedEvent) {
        Warehouse warehouse =warehouseCreatedEvent.getWarehouse();
        return WarehouseCreateAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setWarehouseId(warehouse.getId().getValue())
                .setName(warehouse.getName())
                .setAddressStreet(warehouse.getAddress().getStreet())
                .setAddressCity(warehouse.getAddress().getCity())
                .setAddressPostalCode(warehouse.getAddress().getPostalCode())
                .build();
    }
}