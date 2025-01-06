package com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryDeletedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryUpdatedEvent;

public interface InventoryDeletedMessagePublisher extends DomainEventPublisher<InventoryDeletedEvent> {

}
