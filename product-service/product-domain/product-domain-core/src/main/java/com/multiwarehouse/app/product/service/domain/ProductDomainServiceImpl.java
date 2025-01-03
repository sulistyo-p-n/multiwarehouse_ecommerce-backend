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
        product.validate();
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
        product.setCategory(newProduct.getCategory());
        product.setImages(newProduct.getImages());
        product.setActive(newProduct.isActive());
        product.validate();
        return new ProductUpdatedEvent(
                product,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                productUpdatedEventDomainEventPublisher);
    }

    @Override
    public ProductDeletedEvent validateAndRemoveProduct(Product product, DomainEventPublisher<ProductDeletedEvent> productDeletedEventDomainEventPublisher) {
        product.validate();
        return new ProductDeletedEvent(
                product,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                productDeletedEventDomainEventPublisher);
    }
}
