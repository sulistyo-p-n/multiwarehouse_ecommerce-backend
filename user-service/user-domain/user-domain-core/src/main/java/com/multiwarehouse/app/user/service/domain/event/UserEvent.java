package com.multiwarehouse.app.user.service.domain.event;

import com.multiwarehouse.app.domain.event.DomainEvent;
import com.multiwarehouse.app.user.service.domain.entity.User;

import java.time.ZonedDateTime;

public abstract class UserEvent implements DomainEvent<User> {

    private final User user;
    private final ZonedDateTime createdAt;

    public UserEvent(User user, ZonedDateTime createdAt) {
        this.user = user;
        this.createdAt = createdAt;
    }

    public User getUser() {
        return user;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
