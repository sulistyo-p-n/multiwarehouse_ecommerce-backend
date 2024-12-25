package com.multiwarehouse.app.product.service.messaging.publisher.kafka;

import com.multiwarehouse.app.product.service.domain.event.ProductCreatedEvent;
import com.multiwarehouse.app.product.service.domain.ports.output.message.publisher.product.ProductCreatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductCreatedKafkaMessagePublisher implements ProductCreatedMessagePublisher {
    @Override
    public void publish(ProductCreatedEvent domainEvent) {

    }
}
