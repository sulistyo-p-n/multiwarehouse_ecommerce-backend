package com.multiwarehouse.app.domain.event.publisher;

import com.multiwarehouse.app.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}
