package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.constant.DomainConstants;
import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.event.ProductCreatedEvent;
import com.multiwarehouse.app.product.service.domain.event.ProductDeletedEvent;
import com.multiwarehouse.app.product.service.domain.event.ProductUpdatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ProductDomainServiceImpl implements ProductDomainService {
    @Override
    public ProductCreatedEvent validateAndInitiateProduct(Product product, DomainEventPublisher<ProductCreatedEvent> productCreatedEventDomainEventPublisher) {

        product.validationInitialProduct();
        product.initializeProduct();

        return new ProductCreatedEvent(
                product,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                productCreatedEventDomainEventPublisher);
    }

    @Override
    public ProductUpdatedEvent updateProduct(Product product, DomainEventPublisher<ProductUpdatedEvent> productUpdatedEventDomainEventPublisher) {

        return new ProductUpdatedEvent(
                product,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                productUpdatedEventDomainEventPublisher);
    }

    @Override
    public ProductDeletedEvent deleteProduct(Product product, DomainEventPublisher<ProductDeletedEvent> productDeletedEventDomainEventPublisher) {

        return new ProductDeletedEvent(
                product,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                productDeletedEventDomainEventPublisher);
    }
}
