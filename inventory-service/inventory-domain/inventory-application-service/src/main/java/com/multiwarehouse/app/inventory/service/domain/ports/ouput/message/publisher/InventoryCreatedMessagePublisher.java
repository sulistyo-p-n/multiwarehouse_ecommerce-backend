package com.multiwarehouse.app.inventory.service.domain.ports.ouput.message.publisher;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryCreatedEvent;

public interface InventoryCreatedMessagePublisher extends DomainEventPublisher<InventoryCreatedEvent> {

}
