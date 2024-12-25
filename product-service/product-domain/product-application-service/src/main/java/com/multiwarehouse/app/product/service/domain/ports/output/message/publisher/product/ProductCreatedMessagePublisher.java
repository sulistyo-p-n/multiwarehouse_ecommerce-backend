package com.multiwarehouse.app.product.service.domain.ports.output.message.publisher.product;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.product.service.domain.event.ProductCreatedEvent;

public interface ProductCreatedMessagePublisher extends DomainEventPublisher<ProductCreatedEvent> {

}
