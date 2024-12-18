package com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseDeletedEvent;

public interface WarehouseDeletedMessagePublisher extends DomainEventPublisher<WarehouseDeletedEvent> {
}
