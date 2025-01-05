package com.multiwarehouse.app.product.service.messaging.mapper;

import com.multiwarehouse.app.kafka.product.avro.model.ActionType;
import com.multiwarehouse.app.kafka.product.avro.model.ProductChangeAvroModel;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.event.ProductCreatedEvent;
import com.multiwarehouse.app.product.service.domain.event.ProductDeletedEvent;
import com.multiwarehouse.app.product.service.domain.event.ProductUpdatedEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;

@Component
public class ProductMessagingDataMapper {
    public ProductChangeAvroModel productChangeAvroModelFromProductCreatedEvent(ProductCreatedEvent productCreatedEvent) {
        ProductChangeAvroModel productChangeAvroModel = productChangeAvroModelFromProduct(productCreatedEvent.getProduct());
        productChangeAvroModel.setActionType(ActionType.CREATED);
        productChangeAvroModel.setCreatedAt(productCreatedEvent.getCreatedAt().toInstant());
        return productChangeAvroModel;
    }

    public ProductChangeAvroModel productChangeAvroModelFromProductUpdatedEvent(ProductUpdatedEvent productUpdatedEvent) {
        ProductChangeAvroModel productChangeAvroModel = productChangeAvroModelFromProduct(productUpdatedEvent.getProduct());
        productChangeAvroModel.setActionType(ActionType.UPDATED);
        productChangeAvroModel.setCreatedAt(productUpdatedEvent.getCreatedAt().toInstant());
        return productChangeAvroModel;
    }

    public ProductChangeAvroModel productChangeAvroModelFromProductDeletedEvent(ProductDeletedEvent productDeletedEvent) {
        ProductChangeAvroModel productChangeAvroModel = productChangeAvroModelFromProduct(productDeletedEvent.getProduct());
        productChangeAvroModel.setActionType(productDeletedEvent.isForceDeleted() ? ActionType.HARD_DELETED : ActionType.SOFT_DELETED);
        productChangeAvroModel.setCreatedAt(productDeletedEvent.getCreatedAt().toInstant());
        return productChangeAvroModel;
    }

    private ProductChangeAvroModel productChangeAvroModelFromProduct(Product product) {
        return ProductChangeAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setProductId(product.getId().getValue())
                .setCategoryId(product.getCategory().getId().getValue())
                .setCode(product.getCode())
                .setName(product.getName())
                .setPrice(product.getPrice().getAmount())
                .setActive(product.isActive())
                .setActionType(ActionType.DEFAULT)
                .setCreatedAt(Instant.now())
                .build();
    }
}
