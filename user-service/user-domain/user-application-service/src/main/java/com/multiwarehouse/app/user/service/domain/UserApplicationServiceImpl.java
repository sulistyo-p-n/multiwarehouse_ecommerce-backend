package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.delete.DeleteUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.delete.DeleteUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUsersCommand;
import com.multiwarehouse.app.user.service.domain.dto.login.LoginUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.login.LoginUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.register.RegisterUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.register.RegisterUserResponse;
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
    private final UserLoginCommandHandler userLoginCommandHandler;
    private final UserRegisterCommandHandler userRegisterCommandHandler;

    public UserApplicationServiceImpl(UserCreateCommandHandler userCreateCommandHandler, UserDeleteCommandHandler userDeleteCommandHandler, UserUpdateCommandHandler userUpdateCommandHandler, UserGetCommandHandler userGetCommandHandler, UsersGetCommandHandler usersGetCommandHandler, UserLoginCommandHandler userLoginCommandHandler, UserRegisterCommandHandler userRegisterCommandHandler) {
        this.userCreateCommandHandler = userCreateCommandHandler;
        this.userDeleteCommandHandler = userDeleteCommandHandler;
        this.userUpdateCommandHandler = userUpdateCommandHandler;
        this.userGetCommandHandler = userGetCommandHandler;
        this.usersGetCommandHandler = usersGetCommandHandler;
        this.userLoginCommandHandler = userLoginCommandHandler;
        this.userRegisterCommandHandler = userRegisterCommandHandler;
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

    @Override
    public LoginUserResponse loginUser(LoginUserCommand loginUserCommand) {
        return userLoginCommandHandler.loginUser(loginUserCommand);
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserCommand registerUserCommand) {
        return userRegisterCommandHandler.registerUser(registerUserCommand);
    }
}
