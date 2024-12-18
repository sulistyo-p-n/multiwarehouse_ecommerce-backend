package com.multiwarehouse.app.warehouse.service.messaging.publisher.kafka;

import com.multiwarehouse.app.kafka.order.avro.model.WarehouseCreateAvroModel;
import com.multiwarehouse.app.kafka.producer.KafkaMessageHelper;
import com.multiwarehouse.app.kafka.producer.service.KafkaProducer;
import com.multiwarehouse.app.warehouse.service.domain.config.WarehouseServiceConfigData;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher.WarehouseCreatedMessagePublisher;
import com.multiwarehouse.app.warehouse.service.messaging.mapper.WarehouseMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WarehouseCreateKafkaMessagePublisher implements WarehouseCreatedMessagePublisher {

    private final WarehouseMessagingDataMapper warehouseMessagingDataMapper;
    private final WarehouseServiceConfigData warehouseServiceConfigData;
    private final KafkaProducer<String, WarehouseCreateAvroModel> kafkaProducer;
    private final KafkaMessageHelper warehouseKafkaMessageHelper;

    public WarehouseCreateKafkaMessagePublisher(WarehouseMessagingDataMapper warehouseMessagingDataMapper,
                                                WarehouseServiceConfigData warehouseServiceConfigData,
                                                KafkaProducer<String, WarehouseCreateAvroModel> kafkaProducer,
                                                KafkaMessageHelper warehouseKafkaMessageHelper) {
        this.warehouseMessagingDataMapper = warehouseMessagingDataMapper;
        this.warehouseServiceConfigData = warehouseServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.warehouseKafkaMessageHelper = warehouseKafkaMessageHelper;
    }

    @Override
    public void publish(WarehouseCreatedEvent domainEvent) {
        String warehouseId =domainEvent.getWarehouse().getId().getValue().toString();
        log.info("Received WarehouseCreatedEvent for warehouse id: {}", warehouseId);

        try {
            WarehouseCreateAvroModel warehouseCreateAvroModel = warehouseMessagingDataMapper
                    .warehouseCreatedEventToWarehouseCreateAvroModel(domainEvent);

            kafkaProducer.send(warehouseServiceConfigData.getWarehouseCreateTopicName(),
                    warehouseId,
                    warehouseCreateAvroModel,
                    warehouseKafkaMessageHelper
                            .getKafkaCallback(warehouseServiceConfigData.getWarehouseCreateTopicName(),
                                    warehouseCreateAvroModel,
                                    warehouseId,
                                    "WarehouseCreateAvroModel"));

            log.info("WarehouseCreateAvroModel sent to Kafka for warehouse id: {}", warehouseCreateAvroModel.getWarehouseId());
        } catch (Exception e) {
            log.error("Error while sending WarehouseCreateAvroModel message" +
                    " to kafka with warehouse id: {}, error: {}", warehouseId, e.getMessage());
        }
    }
}
