package com.multiwarehouse.app.product.service.domain.ports.output.message.publisher.product;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.product.service.domain.event.ProductUpdatedEvent;

public interface ProductUpdatedMessagePublisher extends DomainEventPublisher<ProductUpdatedEvent> {

}
