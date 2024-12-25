package com.multiwarehouse.app.product.service.messaging.publisher.kafka;

import com.multiwarehouse.app.product.service.domain.event.ProductDeletedEvent;
import com.multiwarehouse.app.product.service.domain.ports.output.message.publisher.product.ProductDeletedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductDeletedKafkaMessagePublisher implements ProductDeletedMessagePublisher {
    @Override
    public void publish(ProductDeletedEvent domainEvent) {

    }
}
