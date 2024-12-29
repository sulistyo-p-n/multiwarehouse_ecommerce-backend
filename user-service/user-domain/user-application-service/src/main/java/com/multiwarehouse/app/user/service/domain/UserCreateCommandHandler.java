package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserResponse;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.event.UserCreatedEvent;
import com.multiwarehouse.app.user.service.domain.mapper.UserDataMapper;
import com.multiwarehouse.app.user.service.domain.ports.output.message.publsher.user.UserCreatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class UserCreateCommandHandler {
    private final  UserDomainService userDomainService;
    private final UserDataMapper userDataMapper;
    private final UserHelper userHelper;
    private final UserCreatedMessagePublisher userCreatedMessagePublisher;

    public UserCreateCommandHandler(UserDomainService userDomainService, UserDataMapper userDataMapper, UserHelper userHelper, UserCreatedMessagePublisher userCreatedMessagePublisher) {
        this.userDomainService = userDomainService;
        this.userDataMapper = userDataMapper;
        this.userHelper = userHelper;
        this.userCreatedMessagePublisher = userCreatedMessagePublisher;
    }

    @Transactional
    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        User user = userDataMapper.userFromCreateUserCommand(createUserCommand);
        UserCreatedEvent userCreatedEvent = userDomainService.validateAndInitializeUser(user, userCreatedMessagePublisher);
        User userSaved = userHelper.saveUser(userCreatedEvent.getUser());
        log.info("User is created with id: {}", userSaved.getId().getValue());
        return userDataMapper.createUserResponseFromUser(user);
    }
}