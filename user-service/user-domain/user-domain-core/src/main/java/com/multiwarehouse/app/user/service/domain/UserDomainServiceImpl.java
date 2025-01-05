package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.domain.constant.DomainConstants;
import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.event.UserCreatedEvent;
import com.multiwarehouse.app.user.service.domain.event.UserDeletedEvent;
import com.multiwarehouse.app.user.service.domain.event.UserUpdatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
public class UserDomainServiceImpl implements UserDomainService {
    @Override
    public UserCreatedEvent validateAndInitializeUser(User user, DomainEventPublisher<UserCreatedEvent> userCreatedEventDomainEventPublisher) {
        user.validateInitialization();
        user.initialize();
        user.validate();
        return new UserCreatedEvent(
                user,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                userCreatedEventDomainEventPublisher);
    }

    @Override
    public UserUpdatedEvent validateAndSetUser(User user, User newUser, DomainEventPublisher<UserUpdatedEvent> userUpdatedEventDomainEventPublisher) {
        user.validateId();
        user.setUsername(newUser.getUsername());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        user.setActive(newUser.isActive());
        user.setRole(newUser.getRole());
        user.setUserAdminWarehouse(newUser.getUserAdminWarehouse());
        user.setUserProfile(newUser.getUserProfile());
        user.setUserAddresses(newUser.getUserAddresses());
        user.validate();
        return new UserUpdatedEvent(
                user,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                userUpdatedEventDomainEventPublisher);
    }

    @Override
    public UserDeletedEvent removeUser(User user, DomainEventPublisher<UserDeletedEvent> userDeletedEventDomainEventPublisher) {
        user.validate();
        return new UserDeletedEvent(
                user,
                ZonedDateTime.now(ZoneId.of(DomainConstants.UTC)),
                userDeletedEventDomainEventPublisher);
    }
}
