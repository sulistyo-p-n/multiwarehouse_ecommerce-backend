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
    public ProductCreatedEvent validateAndInitializeProduct(Product product, DomainEventPublisher<ProductCreatedEvent> productCreatedEventDomainEventPublisher) {
        product.validateInitialization();
        product.initialize();
        return new ProductCreatedEvent(
                product,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                productCreatedEventDomainEventPublisher);
    }

    @Override
    public ProductUpdatedEvent validateAndSetProduct(Product product, Product newProduct, DomainEventPublisher<ProductUpdatedEvent> productUpdatedEventDomainEventPublisher) {
        product.validateId();
        product.setCode(newProduct.getCode());
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        product.setProductCategory(newProduct.getProductCategory());
        return new ProductUpdatedEvent(
                product,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                productUpdatedEventDomainEventPublisher);
    }

    @Override
    public ProductDeletedEvent removeProduct(Product product, DomainEventPublisher<ProductDeletedEvent> productDeletedEventDomainEventPublisher) {
        product.validate();
        return new ProductDeletedEvent(
                product,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                productDeletedEventDomainEventPublisher);
    }
}
