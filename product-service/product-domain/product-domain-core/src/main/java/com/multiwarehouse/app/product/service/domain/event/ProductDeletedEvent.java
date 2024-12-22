package com.multiwarehouse.app.product.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.product.service.domain.entity.Product;

import java.time.ZonedDateTime;

public class ProductDeletedEvent extends ProductEvent {

    private final DomainEventPublisher<ProductDeletedEvent> productDeletedEventDomainEventPublisher;

    public ProductDeletedEvent(Product product,
                               ZonedDateTime createdAt,
                               DomainEventPublisher<ProductDeletedEvent> productDeletedEventDomainEventPublisher) {
        super(product, createdAt);
        this.productDeletedEventDomainEventPublisher = productDeletedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        productDeletedEventDomainEventPublisher.publish(this);
    }
}
