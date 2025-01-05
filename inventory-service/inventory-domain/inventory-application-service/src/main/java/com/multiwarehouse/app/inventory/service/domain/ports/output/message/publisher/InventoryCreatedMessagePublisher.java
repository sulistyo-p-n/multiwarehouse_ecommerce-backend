package com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryCreatedEvent;

public interface InventoryCreatedMessagePublisher extends DomainEventPublisher<InventoryCreatedEvent> {

}
