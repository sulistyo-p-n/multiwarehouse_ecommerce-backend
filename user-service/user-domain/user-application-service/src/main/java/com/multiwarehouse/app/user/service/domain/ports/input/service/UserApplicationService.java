package com.multiwarehouse.app.user.service.domain.ports.input.service;

import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.delete.DeleteUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.delete.DeleteUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUsersCommand;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUsersResponse;
import com.multiwarehouse.app.user.service.domain.dto.update.UpdateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.update.UpdateUserResponse;
import jakarta.validation.Valid;

public interface UserApplicationService {
    public CreateUserResponse createUser(@Valid CreateUserCommand createUserCommand);

    public UpdateUserResponse updateUser(@Valid UpdateUserCommand updateUserCommand);

    public DeleteUserResponse deleteUser(@Valid DeleteUserCommand deleteUserCommand);

    public GetUserResponse getUser(@Valid GetUserCommand getUserCommand);

    public GetUsersResponse getUsers(@Valid GetUsersCommand getUsersCommand);
}
