package com.multiwarehouse.app.inventory.service.messaging.listener.kafka;

import com.multiwarehouse.app.inventory.service.domain.ports.input.message.listener.ProductChangeMessageListener;
import com.multiwarehouse.app.inventory.service.messaging.mapper.ProductMessagingDataMapper;
import com.multiwarehouse.app.kafka.consumer.KafkaConsumer;
import com.multiwarehouse.app.kafka.product.avro.model.ProductChangeAvroModel;
import com.multiwarehouse.app.kafka.product.avro.model.ActionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ProductChangeKafkaListener implements KafkaConsumer<ProductChangeAvroModel> {
    private final ProductChangeMessageListener productChangeMessageListener;
    private final ProductMessagingDataMapper productMessagingDataMapper;

    public ProductChangeKafkaListener(ProductChangeMessageListener productChangeMessageListener, ProductMessagingDataMapper productMessagingDataMapper) {
        this.productChangeMessageListener = productChangeMessageListener;
        this.productMessagingDataMapper = productMessagingDataMapper;
    }

    @Override
    @KafkaListener(id = "${kafka-consumer-config.product-change-consumer-group-id}", topics = "${inventory-service.product-change-topic-name}")
    public void receive(
            @Payload List<ProductChangeAvroModel> messages,
            @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
            @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
            @Header(KafkaHeaders.OFFSET) List<Long> offsets) {

        log.info("{} number of payment responses received with keys:{}, partitions:{} and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(productChangeAvroModel -> {
            log.info("ProductChangeAvroModel receive with Id:{}", productChangeAvroModel.getId());
            if (productChangeAvroModel.getActionType() == ActionType.CREATED) {
                log.info("Processing Product Create with ProductId:{}", productChangeAvroModel.getProductId());
                productChangeMessageListener.productCreated(productMessagingDataMapper.productFromProductChangeAvroModel(productChangeAvroModel));
            } else if (productChangeAvroModel.getActionType() == ActionType.UPDATED) {
                log.info("Processing Product Update with ProductId:{}", productChangeAvroModel.getProductId());
                productChangeMessageListener.productUpdated(productMessagingDataMapper.productFromProductChangeAvroModel(productChangeAvroModel));
            } else if (productChangeAvroModel.getActionType() == ActionType.SOFT_DELETED) {
                log.info("Processing Product Soft Delete with ProductId:{}", productChangeAvroModel.getProductId());
                productChangeMessageListener.productSoftDeleted(productMessagingDataMapper.productFromProductChangeAvroModel(productChangeAvroModel));
            } else if (productChangeAvroModel.getActionType() == ActionType.HARD_DELETED) {
                log.info("Processing Product Hard Delete with ProductId:{}", productChangeAvroModel.getProductId());
                productChangeMessageListener.productHardDeleted(productMessagingDataMapper.productFromProductChangeAvroModel(productChangeAvroModel));
            }
        });
    }
}
