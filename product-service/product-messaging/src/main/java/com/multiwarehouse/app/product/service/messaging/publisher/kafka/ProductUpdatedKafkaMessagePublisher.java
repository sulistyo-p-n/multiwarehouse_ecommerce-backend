package com.multiwarehouse.app.product.service.messaging.publisher.kafka;

import com.multiwarehouse.app.product.service.domain.event.ProductUpdatedEvent;
import com.multiwarehouse.app.product.service.domain.ports.output.message.publisher.product.ProductUpdatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductUpdatedKafkaMessagePublisher implements ProductUpdatedMessagePublisher {
    @Override
    public void publish(ProductUpdatedEvent domainEvent) {

    }
}
