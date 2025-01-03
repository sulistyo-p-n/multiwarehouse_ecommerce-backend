package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.delete.DeleteUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.delete.DeleteUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUsersCommand;
import com.multiwarehouse.app.user.service.domain.dto.update.UpdateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.update.UpdateUserResponse;
import com.multiwarehouse.app.user.service.domain.ports.input.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
public class UserApplicationServiceImpl implements UserApplicationService {
    private final UserCreateCommandHandler userCreateCommandHandler;
    private final UserDeleteCommandHandler userDeleteCommandHandler;
    private final UserUpdateCommandHandler userUpdateCommandHandler;
    private final UserGetCommandHandler userGetCommandHandler;
    private final UsersGetCommandHandler usersGetCommandHandler;

    public UserApplicationServiceImpl(UserCreateCommandHandler userCreateCommandHandler, UserDeleteCommandHandler userDeleteCommandHandler, UserUpdateCommandHandler userUpdateCommandHandler, UserGetCommandHandler userGetCommandHandler, UsersGetCommandHandler usersGetCommandHandler) {
        this.userCreateCommandHandler = userCreateCommandHandler;
        this.userDeleteCommandHandler = userDeleteCommandHandler;
        this.userUpdateCommandHandler = userUpdateCommandHandler;
        this.userGetCommandHandler = userGetCommandHandler;
        this.usersGetCommandHandler = usersGetCommandHandler;
    }

    @Override
    public CreateUserResponse createUser(CreateUserCommand createUserCommand) {
        return userCreateCommandHandler.createUser(createUserCommand);
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserCommand updateUserCommand) {
        return userUpdateCommandHandler.updateUser(updateUserCommand);
    }

    @Override
    public DeleteUserResponse deleteUser(DeleteUserCommand deleteUserCommand) {
        return userDeleteCommandHandler.deleteUser(deleteUserCommand);
    }

    @Override
    public List<GetUserResponse> getUsers(GetUsersCommand getUsersCommand) {
        return usersGetCommandHandler.getUsers(getUsersCommand);
    }

    @Override
    public GetUserResponse getUser(GetUserCommand getUserCommand) {
        return userGetCommandHandler.getUser(getUserCommand);
    }
}
