package com.multiwarehouse.app.warehouse.service.messaging.publisher.kafka;

import com.multiwarehouse.app.kafka.producer.KafkaMessageHelper;
import com.multiwarehouse.app.kafka.producer.service.KafkaProducer;
import com.multiwarehouse.app.kafka.warehouse.avro.model.WarehouseChangeAvroModel;
import com.multiwarehouse.app.warehouse.service.domain.config.WarehouseServiceConfigData;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseDeletedEvent;
import com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher.WarehouseDeletedMessagePublisher;
import com.multiwarehouse.app.warehouse.service.messaging.mapper.WarehouseMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WarehouseDeleteKafkaMessagePublisher implements WarehouseDeletedMessagePublisher {

    private final WarehouseMessagingDataMapper warehouseMessagingDataMapper;
    private final WarehouseServiceConfigData warehouseServiceConfigData;
    private final KafkaProducer<String, WarehouseChangeAvroModel> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;

    public WarehouseDeleteKafkaMessagePublisher(WarehouseMessagingDataMapper warehouseMessagingDataMapper,
                                                WarehouseServiceConfigData warehouseServiceConfigData,
                                                KafkaProducer<String, WarehouseChangeAvroModel> kafkaProducer,
                                                KafkaMessageHelper kafkaMessageHelper) {
        this.warehouseMessagingDataMapper = warehouseMessagingDataMapper;
        this.warehouseServiceConfigData = warehouseServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(WarehouseDeletedEvent domainEvent) {
        String warehouseId = domainEvent.getWarehouse().getId().getValue().toString();
        log.info("Received WarehouseDeletedEvent for warehouse id: {}", warehouseId);

        try {
            WarehouseChangeAvroModel warehouseChangeAvroModel = warehouseMessagingDataMapper
                    .warehouseChangeAvroModelFromWarehouseDeletedEvent(domainEvent);

            kafkaProducer.send(warehouseServiceConfigData.getWarehouseChangeTopicName(),
                    warehouseId,
                    warehouseChangeAvroModel,
                    kafkaMessageHelper
                            .getKafkaCallback(warehouseServiceConfigData.getWarehouseChangeTopicName(),
                                    warehouseChangeAvroModel,
                                    warehouseId,
                                    "WarehouseChangeAvroModel"));

            log.info("WarehouseChangeAvroModel sent to Kafka for warehouse id: {}", warehouseChangeAvroModel.getWarehouseId());
        } catch (Exception e) {
            log.error("Error while sending WarehouseChangeAvroModel message" +
                    " to kafka with warehouse id: {}, error: {}", warehouseId, e.getMessage());
        }
    }
}
