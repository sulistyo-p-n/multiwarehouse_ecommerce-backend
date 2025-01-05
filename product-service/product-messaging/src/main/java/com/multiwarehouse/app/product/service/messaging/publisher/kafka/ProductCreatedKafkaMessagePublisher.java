package com.multiwarehouse.app.product.service.messaging.publisher.kafka;

import com.multiwarehouse.app.kafka.producer.KafkaMessageHelper;
import com.multiwarehouse.app.kafka.producer.service.KafkaProducer;
import com.multiwarehouse.app.kafka.product.avro.model.ProductChangeAvroModel;
import com.multiwarehouse.app.product.service.domain.config.ProductServiceConfigData;
import com.multiwarehouse.app.product.service.domain.event.ProductCreatedEvent;
import com.multiwarehouse.app.product.service.domain.ports.output.message.publisher.product.ProductCreatedMessagePublisher;
import com.multiwarehouse.app.product.service.messaging.mapper.ProductMessagingDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductCreatedKafkaMessagePublisher implements ProductCreatedMessagePublisher {
    private final ProductMessagingDataMapper productMessagingDataMapper;
    private final ProductServiceConfigData productServiceConfigData;
    private final KafkaProducer<String, ProductChangeAvroModel> kafkaProducer;
    private final KafkaMessageHelper kafkaMessageHelper;

    public ProductCreatedKafkaMessagePublisher(ProductMessagingDataMapper productMessagingDataMapper,
                                               ProductServiceConfigData productServiceConfigData,
                                               KafkaProducer<String, ProductChangeAvroModel> kafkaProducer,
                                               KafkaMessageHelper kafkaMessageHelper) {
        this.productMessagingDataMapper = productMessagingDataMapper;
        this.productServiceConfigData = productServiceConfigData;
        this.kafkaProducer = kafkaProducer;
        this.kafkaMessageHelper = kafkaMessageHelper;
    }

    @Override
    public void publish(ProductCreatedEvent domainEvent) {
        String productId = domainEvent.getProduct().getId().getValue().toString();
        log.info("Received ProductCreatedEvent for id: {}", productId);

        try {
            ProductChangeAvroModel productChangeAvroModel = productMessagingDataMapper
                    .productChangeAvroModelFromProductCreatedEvent(domainEvent);

            kafkaProducer.send(productServiceConfigData.getProductChangeTopicName(),
                    productId,
                    productChangeAvroModel,
                    kafkaMessageHelper
                            .getKafkaCallback(productServiceConfigData.getProductChangeTopicName(),
                                    productChangeAvroModel,
                                    productId,
                                    "ProductChangeAvroModel"));

            log.info("ProductChangeAvroModel sent to Kafka for id: {}", productChangeAvroModel.getProductId());
        } catch (Exception e) {
            log.error("Error while sending ProductChangeAvroModel message" +
                    " to kafka with id: {}, error: {}", productId, e.getMessage());
        }
    }
}
