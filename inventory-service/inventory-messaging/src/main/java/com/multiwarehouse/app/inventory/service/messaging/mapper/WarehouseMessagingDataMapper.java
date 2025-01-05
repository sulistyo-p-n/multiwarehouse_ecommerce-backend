package com.multiwarehouse.app.inventory.service.messaging.mapper;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.entity.Warehouse;
import com.multiwarehouse.app.kafka.warehouse.avro.model.WarehouseChangeAvroModel;
import org.springframework.stereotype.Component;

@Component
public class WarehouseMessagingDataMapper {
    public Warehouse warehouseFromWarehouseChangeAvroModel(WarehouseChangeAvroModel warehouseChangeAvroModel) {
        return Warehouse.builder()
                .withId(new WarehouseId(warehouseChangeAvroModel.getWarehouseId()))
                .withCode(warehouseChangeAvroModel.getCode())
                .withName(warehouseChangeAvroModel.getName())
                .withActive(warehouseChangeAvroModel.getActive())
                .build();
    }
}
