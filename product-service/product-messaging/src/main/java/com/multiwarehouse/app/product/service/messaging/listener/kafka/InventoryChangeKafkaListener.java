package com.multiwarehouse.app.product.service.messaging.listener.kafka;

import com.multiwarehouse.app.kafka.consumer.KafkaConsumer;
import com.multiwarehouse.app.kafka.inventory.avro.model.InventoryChangeAvroModel;
import com.multiwarehouse.app.kafka.inventory.avro.model.ActionType;
import com.multiwarehouse.app.product.service.domain.ports.input.message.listener.InventoryChangeMessageListener;
import com.multiwarehouse.app.product.service.messaging.mapper.InventoryMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class InventoryChangeKafkaListener implements KafkaConsumer<InventoryChangeAvroModel> {
    private final InventoryChangeMessageListener inventoryChangeMessageListener;
    private final InventoryMessagingDataMapper inventoryMessagingDataMapper;

    public InventoryChangeKafkaListener(InventoryChangeMessageListener inventoryChangeMessageListener, InventoryMessagingDataMapper inventoryMessagingDataMapper) {
        this.inventoryChangeMessageListener = inventoryChangeMessageListener;
        this.inventoryMessagingDataMapper = inventoryMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.inventory-change-consumer-group-id}", topics = "${product-service.inventory-change-topic-name}")
    public void receive(
            @Payload List<InventoryChangeAvroModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(inventoryChangeAvroModel -> {
            log.info("InventoryChangeAvroModel receive with Id:{}", inventoryChangeAvroModel.getId());
            if (inventoryChangeAvroModel.getActionType() == ActionType.CREATED) {
                log.info("Processing Inventory Create with Id:{}", inventoryChangeAvroModel.getInventoryId());
                inventoryChangeMessageListener.inventoryCreated(inventoryMessagingDataMapper.inventoryFromInventoryChangeAvroModel(inventoryChangeAvroModel));
            } else if (inventoryChangeAvroModel.getActionType() == ActionType.UPDATED) {
                log.info("Processing Inventory Update with Id:{}", inventoryChangeAvroModel.getInventoryId());
                inventoryChangeMessageListener.inventoryUpdated(inventoryMessagingDataMapper.inventoryFromInventoryChangeAvroModel(inventoryChangeAvroModel));
            } else if (inventoryChangeAvroModel.getActionType() == ActionType.SOFT_DELETED) {
                log.info("Processing Inventory Soft Delete with Id:{}", inventoryChangeAvroModel.getInventoryId());
                inventoryChangeMessageListener.inventorySoftDeleted(inventoryMessagingDataMapper.inventoryFromInventoryChangeAvroModel(inventoryChangeAvroModel));
            } else if (inventoryChangeAvroModel.getActionType() == ActionType.HARD_DELETED) {
                log.info("Processing Inventory Hard Delete with Id:{}", inventoryChangeAvroModel.getInventoryId());
                inventoryChangeMessageListener.inventoryHardDeleted(inventoryMessagingDataMapper.inventoryFromInventoryChangeAvroModel(inventoryChangeAvroModel));
            }
        });
    }
}
