package com.multiwarehouse.app.user.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserUpdatedEvent extends UserEvent {

    private final DomainEventPublisher<UserUpdatedEvent> userUpdatedEventDomainEventPublisher;

    public UserUpdatedEvent(User user,
                            ZonedDateTime createdAt,
                            DomainEventPublisher<UserUpdatedEvent> userUpdatedEventDomainEventPublisher) {
        super(user, createdAt);
        this.userUpdatedEventDomainEventPublisher = userUpdatedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        userUpdatedEventDomainEventPublisher.publish(this);
    }
}
