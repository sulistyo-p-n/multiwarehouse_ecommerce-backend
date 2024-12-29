package com.multiwarehouse.app.user.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserDeletedEvent extends UserEvent {

    private final DomainEventPublisher<UserDeletedEvent> userDeletedEventDomainEventPublisher;

    public UserDeletedEvent(User user,
                            ZonedDateTime createdAt,
                            DomainEventPublisher<UserDeletedEvent> userDeletedEventDomainEventPublisher) {
        super(user, createdAt);
        this.userDeletedEventDomainEventPublisher = userDeletedEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        userDeletedEventDomainEventPublisher.publish(this);
    }
}
