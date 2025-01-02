package com.multiwarehouse.app.inventory.service.domain.ports.ouput.message.publisher;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;

public interface InventoryStockChangedMessagePublisher extends DomainEventPublisher<InventoryStockChangedEvent> {

}
