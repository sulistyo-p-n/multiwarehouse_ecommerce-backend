package com.multiwarehouse.app.user.service.messaging.listener.kafka.warehouse;

import com.multiwarehouse.app.kafka.consumer.KafkaConsumer;
import com.multiwarehouse.app.kafka.warehouse.avro.model.ActionType;
import com.multiwarehouse.app.kafka.warehouse.avro.model.WarehouseChangeAvroModel;
import com.multiwarehouse.app.user.service.domain.ports.input.message.listener.WarehouseChangeMessageListener;
import com.multiwarehouse.app.user.service.messaging.mapper.WarehouseMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class WarehouseChangeKafkaListener implements KafkaConsumer<WarehouseChangeAvroModel> {
    private final WarehouseChangeMessageListener warehouseChangeMessageListener;
    private final WarehouseMessagingDataMapper warehouseMessagingDataMapper;

    public WarehouseChangeKafkaListener(WarehouseChangeMessageListener warehouseChangeMessageListener, WarehouseMessagingDataMapper warehouseMessagingDataMapper) {
        this.warehouseChangeMessageListener = warehouseChangeMessageListener;
        this.warehouseMessagingDataMapper = warehouseMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.warehouse-change-consumer-group-id}", topics = "${user-service.warehouse-change-topic-name}")
    public void receive(
            @Payload List<WarehouseChangeAvroModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(warehouseChangeAvroModel -> {
            log.info("WarehouseChangeAvroModel receive with Id:{}", warehouseChangeAvroModel.getId());
            if (warehouseChangeAvroModel.getActionType() == ActionType.CREATED) {
                log.info("Processing Warehouse Create with WarehouseId:{}", warehouseChangeAvroModel.getWarehouseId());
                warehouseChangeMessageListener.warehouseCreated(warehouseMessagingDataMapper.warehouseFromWarehouseChangeAvroModel(warehouseChangeAvroModel));
            } else if (warehouseChangeAvroModel.getActionType() == ActionType.UPDATED) {
                log.info("Processing Warehouse Update with WarehouseId:{}", warehouseChangeAvroModel.getWarehouseId());
                warehouseChangeMessageListener.warehouseUpdated(warehouseMessagingDataMapper.warehouseFromWarehouseChangeAvroModel(warehouseChangeAvroModel));
            } else if (warehouseChangeAvroModel.getActionType() == ActionType.SOFT_DELETED) {
                log.info("Processing Warehouse Soft Delete with WarehouseId:{}", warehouseChangeAvroModel.getWarehouseId());
                warehouseChangeMessageListener.warehouseSoftDeleted(warehouseMessagingDataMapper.warehouseFromWarehouseChangeAvroModel(warehouseChangeAvroModel));
            } else if (warehouseChangeAvroModel.getActionType() == ActionType.HARD_DELETED) {
                log.info("Processing Warehouse Hard Delete with WarehouseId:{}", warehouseChangeAvroModel.getWarehouseId());
                warehouseChangeMessageListener.warehouseHardDeleted(warehouseMessagingDataMapper.warehouseFromWarehouseChangeAvroModel(warehouseChangeAvroModel));
            }
        });
    }
}
