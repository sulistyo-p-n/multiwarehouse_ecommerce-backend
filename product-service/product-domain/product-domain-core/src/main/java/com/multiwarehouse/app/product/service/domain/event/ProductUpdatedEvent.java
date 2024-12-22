package com.multiwarehouse.app.product.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.product.service.domain.entity.Product;

import java.time.ZonedDateTime;

public class ProductUpdatedEvent extends ProductEvent {

    private final DomainEventPublisher<ProductUpdatedEvent> productUpdatedEventDomainEventPublisher;

    public ProductUpdatedEvent(Product product,
                               ZonedDateTime createdAt,
                               DomainEventPublisher<ProductUpdatedEvent> productUpdatedEventDomainEventPublisher) {
        super(product, createdAt);
        this.productUpdatedEventDomainEventPublisher = productUpdatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        productUpdatedEventDomainEventPublisher.publish(this);
    }
}
