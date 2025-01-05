package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.user.service.domain.dto.delete.DeleteUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.delete.DeleteUserResponse;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.event.UserDeletedEvent;
import com.multiwarehouse.app.user.service.domain.ports.output.message.publisher.user.UserDeletedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class UserDeleteCommandHandler {
    private final  UserDomainService userDomainService;
    private final UserHelper userHelper;
    private final UserDeletedMessagePublisher userDeletedMessagePublisher;

    public UserDeleteCommandHandler(UserDomainService userDomainService, UserHelper userHelper, UserDeletedMessagePublisher userDeletedMessagePublisher) {
        this.userDomainService = userDomainService;
        this.userHelper = userHelper;
        this.userDeletedMessagePublisher = userDeletedMessagePublisher;
    }

    @Transactional
    public DeleteUserResponse deleteUser(DeleteUserCommand deleteUserCommand) {
        UserId userId = new UserId(deleteUserCommand.getId());
        User user = userHelper.findUserById(userId);
        UserDeletedEvent userDeletedEvent = userDomainService.removeUser(user, userDeletedMessagePublisher);
        userDeletedEvent.setForceDeleted(deleteUserCommand.isForceDelete());
        userHelper.deleteUser(userDeletedEvent.getUser(), deleteUserCommand.isForceDelete());
        log.info("User is deleted with id: {}", userDeletedEvent.getUser().getId().getValue());
        userDeletedMessagePublisher.publish(userDeletedEvent);
        return DeleteUserResponse.builder().id(userDeletedEvent.getUser().getId().getValue()).build();
    }
}
