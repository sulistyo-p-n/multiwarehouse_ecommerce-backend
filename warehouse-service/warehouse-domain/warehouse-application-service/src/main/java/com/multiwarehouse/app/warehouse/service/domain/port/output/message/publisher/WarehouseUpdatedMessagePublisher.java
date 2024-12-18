package com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseUpdatedEvent;

public interface WarehouseUpdatedMessagePublisher extends DomainEventPublisher<WarehouseUpdatedEvent> {
}
