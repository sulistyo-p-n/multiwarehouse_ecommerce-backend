package com.multiwarehouse.app.inventory.service.messaging.publisher.kafka;

import com.multiwarehouse.app.inventory.service.domain.config.InventoryServiceConfigData;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryCreatedEvent;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryCreatedMessagePublisher;
import com.multiwarehouse.app.inventory.service.messaging.mapper.InventoryMessagingDataMapper;
import com.multiwarehouse.app.kafka.inventory.avro.model.InventoryChangeAvroModel;
import com.multiwarehouse.app.kafka.producer.KafkaMessageHelper;
import com.multiwarehouse.app.kafka.producer.service.KafkaProducer;
import com.multiwarehouse.app.kafka.warehouse.avro.model.WarehouseChangeAvroModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryCreatedKafkaMessagePublisher implements InventoryCreatedMessagePublisher {
    private final InventoryMessagingDataMapper inventoryMessagingDataMapper;
    private final InventoryServiceConfigData inventoryServiceConfigData;
    private final KafkaProducer<String, InventoryChangeAvroModel> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;

    public InventoryCreatedKafkaMessagePublisher(InventoryMessagingDataMapper inventoryMessagingDataMapper,
                                                 InventoryServiceConfigData inventoryServiceConfigData,
                                                 KafkaProducer<String, InventoryChangeAvroModel> kafkaProducer,
                                                 KafkaMessageHelper kafkaMessageHelper) {
        this.inventoryMessagingDataMapper = inventoryMessagingDataMapper;
        this.inventoryServiceConfigData = inventoryServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(InventoryCreatedEvent domainEvent) {
        String id = domainEvent.getInventory().getId().getValue().toString();
        log.info("Received InventoryCreatedEvent for id: {}", id);

        try {
            InventoryChangeAvroModel inventoryChangeAvroModel = inventoryMessagingDataMapper
                    .inventoryChangeAvroModelFromInventoryCreatedEvent(domainEvent);

            kafkaProducer.send(inventoryServiceConfigData.getInventoryChangeTopicName(),
                    id,
                    inventoryChangeAvroModel,
                    kafkaMessageHelper
                            .getKafkaCallback(inventoryServiceConfigData.getInventoryChangeTopicName(),
                                    inventoryChangeAvroModel,
                                    id,
                                    "InventoryChangeAvroModel"));

            log.info("InventoryChangeAvroModel sent to Kafka for id: {}", inventoryChangeAvroModel.getInventoryId());
        } catch (Exception e) {
            log.error("Error while sending InventoryChangeAvroModel message" +
                    " to kafka with id: {}, error: {}", id, e.getMessage());
        }
    }
}
