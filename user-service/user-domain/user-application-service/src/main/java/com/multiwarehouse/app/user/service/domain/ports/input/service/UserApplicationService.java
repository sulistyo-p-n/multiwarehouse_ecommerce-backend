package com.multiwarehouse.app.user.service.domain.ports.input.service;

import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.delete.DeleteUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.delete.DeleteUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUsersCommand;
import com.multiwarehouse.app.user.service.domain.dto.update.UpdateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.update.UpdateUserResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface UserApplicationService {
    public CreateUserResponse createUser(@Valid CreateUserCommand createUserCommand);

    public UpdateUserResponse updateUser(@Valid UpdateUserCommand updateUserCommand);

    public DeleteUserResponse deleteUser(@Valid DeleteUserCommand deleteUserCommand);

    public List<GetUserResponse> getUsers(@Valid GetUsersCommand getUsersCommand);

    public GetUserResponse getUser(@Valid GetUserCommand getUserCommand);
}
