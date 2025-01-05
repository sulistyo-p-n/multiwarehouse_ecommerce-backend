package com.multiwarehouse.app.user.service.domain.event;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public class UserDeletedEvent extends UserEvent {

    private final DomainEventPublisher<UserDeletedEvent> userDeletedEventDomainEventPublisher;

    private boolean forceDeleted;

    public UserDeletedEvent(User user,
                            ZonedDateTime createdAt,
                            DomainEventPublisher<UserDeletedEvent> userDeletedEventDomainEventPublisher) {
        super(user, createdAt);
        this.userDeletedEventDomainEventPublisher = userDeletedEventDomainEventPublisher;
    }

    public boolean isForceDeleted() {
        return forceDeleted;
    }

    public void setForceDeleted(boolean forceDeleted) {
        this.forceDeleted = forceDeleted;
    }

    @Override
    public void fire() {
        userDeletedEventDomainEventPublisher.publish(this);
    }
}
