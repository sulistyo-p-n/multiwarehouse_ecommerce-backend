package com.multiwarehouse.app.user.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserCreatedEvent extends UserEvent {

    private final DomainEventPublisher<UserCreatedEvent> userCreatedEventDomainEventPublisher;

    public UserCreatedEvent(User user,
                               ZonedDateTime createdAt,
                               DomainEventPublisher<UserCreatedEvent> userCreatedEventDomainEventPublisher) {
        super(user, createdAt);
        this.userCreatedEventDomainEventPublisher = userCreatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        userCreatedEventDomainEventPublisher.publish(this);
    }
}
