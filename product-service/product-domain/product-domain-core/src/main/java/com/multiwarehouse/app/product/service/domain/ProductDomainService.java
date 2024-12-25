package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.event.ProductCreatedEvent;
import com.multiwarehouse.app.product.service.domain.event.ProductDeletedEvent;
import com.multiwarehouse.app.product.service.domain.event.ProductUpdatedEvent;

public interface ProductDomainService {
    ProductCreatedEvent validateAndInitializeProduct(Product product, DomainEventPublisher<ProductCreatedEvent> productCreatedEventDomainEventPublisher);
    ProductUpdatedEvent validateAndSetProduct(Product product, Product newProduct, DomainEventPublisher<ProductUpdatedEvent> productUpdatedEventDomainEventPublisher);
    ProductDeletedEvent removeProduct(Product product, DomainEventPublisher<ProductDeletedEvent> productDeletedEventDomainEventPublisher);
}
