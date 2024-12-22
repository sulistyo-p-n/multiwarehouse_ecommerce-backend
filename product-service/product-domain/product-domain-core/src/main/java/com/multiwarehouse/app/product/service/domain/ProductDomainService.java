package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.entity.ProductImage;
import com.multiwarehouse.app.product.service.domain.event.ProductCreatedEvent;
import com.multiwarehouse.app.product.service.domain.event.ProductDeletedEvent;
import com.multiwarehouse.app.product.service.domain.event.ProductUpdatedEvent;

public interface ProductDomainService {
    ProductCreatedEvent validateAndInitiateProduct(
            Product product,
            DomainEventPublisher<ProductCreatedEvent> productCreatedEventDomainEventPublisher);

    ProductUpdatedEvent updateProduct(
            Product product,
            DomainEventPublisher<ProductUpdatedEvent> productUpdatedEventDomainEventPublisher);

    ProductDeletedEvent deleteProduct(
            Product product,
            DomainEventPublisher<ProductDeletedEvent> productDeletedEventDomainEventPublisher);
}
