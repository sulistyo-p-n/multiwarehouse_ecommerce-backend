package com.multiwarehouse.app.inventory.service.messaging.publisher.kafka;

import com.multiwarehouse.app.inventory.service.domain.config.InventoryServiceConfigData;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryDeletedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryUpdatedEvent;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryDeletedMessagePublisher;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryUpdatedMessagePublisher;
import com.multiwarehouse.app.inventory.service.messaging.mapper.InventoryMessagingDataMapper;
import com.multiwarehouse.app.kafka.inventory.avro.model.InventoryChangeAvroModel;
import com.multiwarehouse.app.kafka.producer.KafkaMessageHelper;
import com.multiwarehouse.app.kafka.producer.service.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryDeletedKafkaMessagePublisher implements InventoryDeletedMessagePublisher {
    private final InventoryMessagingDataMapper inventoryMessagingDataMapper;
    private final InventoryServiceConfigData inventoryServiceConfigData;
    private final KafkaProducer<String, InventoryChangeAvroModel> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;

    public InventoryDeletedKafkaMessagePublisher(InventoryMessagingDataMapper inventoryMessagingDataMapper,
                                                 InventoryServiceConfigData inventoryServiceConfigData,
                                                 KafkaProducer<String, InventoryChangeAvroModel> kafkaProducer,
                                                 KafkaMessageHelper kafkaMessageHelper) {
        this.inventoryMessagingDataMapper = inventoryMessagingDataMapper;
        this.inventoryServiceConfigData = inventoryServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(InventoryDeletedEvent domainEvent) {
        String id = domainEvent.getInventory().getId().getValue().toString();
        log.info("Received InventoryDeletedEvent for id: {}", id);

        try {
            InventoryChangeAvroModel inventoryChangeAvroModel = inventoryMessagingDataMapper
                    .inventoryChangeAvroModelFromInventoryDeletedEvent(domainEvent);

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
