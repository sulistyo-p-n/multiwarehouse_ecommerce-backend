package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.user.service.domain.dto.update.UpdateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.update.UpdateUserResponse;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.event.UserUpdatedEvent;
import com.multiwarehouse.app.user.service.domain.mapper.UserDataMapper;
import com.multiwarehouse.app.user.service.domain.ports.output.message.publisher.user.UserUpdatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class UserUpdateCommandHandler {
    private final  UserDomainService userDomainService;
    private final UserDataMapper userDataMapper;
    private final UserHelper userHelper;
    private final UserUpdatedMessagePublisher userUpdatedMessagePublisher;

    public UserUpdateCommandHandler(UserDomainService userDomainService, UserDataMapper userDataMapper, UserHelper userHelper, UserUpdatedMessagePublisher userUpdatedMessagePublisher) {
        this.userDomainService = userDomainService;
        this.userDataMapper = userDataMapper;
        this.userHelper = userHelper;
        this.userUpdatedMessagePublisher = userUpdatedMessagePublisher;
    }

    @Transactional
    public UpdateUserResponse updateUser(UpdateUserCommand updateUserCommand) {
        UserId userId = new UserId(updateUserCommand.getId());
        User user = userHelper.findUserById(userId);
        User newUser = userDataMapper.userFromUpdateUserCommand(updateUserCommand);
        UserUpdatedEvent userUpdatedEvent = userDomainService.validateAndSetUser(user, newUser, userUpdatedMessagePublisher);
        User userSaved = userHelper.persistUser(userUpdatedEvent.getUser());
        log.info("User is updated with id: {}", userSaved.getId().getValue());
        userUpdatedMessagePublisher.publish(userUpdatedEvent);
        return userDataMapper.updateUserResponseFromUser(userSaved);
    }
}
