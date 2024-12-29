package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.event.UserCreatedEvent;
import com.multiwarehouse.app.user.service.domain.event.UserDeletedEvent;
import com.multiwarehouse.app.user.service.domain.event.UserUpdatedEvent;

public interface UserDomainService {
    UserCreatedEvent validateAndInitializeUser(User user, DomainEventPublisher<UserCreatedEvent> userCreatedEventDomainEventPublisher);
    UserUpdatedEvent validateAndSetUser(User user, User newUser, DomainEventPublisher<UserUpdatedEvent> userUpdatedEventDomainEventPublisher);
    UserDeletedEvent removeUser(User user, DomainEventPublisher<UserDeletedEvent> userDeletedEventDomainEventPublisher);

}
