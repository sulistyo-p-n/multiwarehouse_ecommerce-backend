package com.multiwarehouse.app.user.service.domain.rest;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/users", produces = "application/vnd.api.v1+json")
public class UserController {
    private final UserApplicationService userApplicationService;

    public UserController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<GetUserResponse>> getUsers(GetUsersCommand getUsersCommand) {
        log.info("Getting Users: {}", getUsersCommand);
        List<GetUserResponse> getUsersResponse = userApplicationService.getUsers(getUsersCommand);
        log.info("Returning Users size: {}", getUsersResponse.size());
        return ResponseEntity.ok(getUsersResponse);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<GetUserResponse> getUser(@PathVariable("id") UUID id) {
        log.info("Getting User by id: {}", id);
        GetUserCommand getUserCommand = GetUserCommand.builder().id(id).build();
        GetUserResponse getUserResponse = userApplicationService.getUser(getUserCommand);
        log.info("Returning User: {}", getUserResponse);
        return ResponseEntity.ok(getUserResponse);
    }

    @PostMapping
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserCommand createUserCommand) {
        log.info("Creating User: {}", createUserCommand);
        CreateUserResponse createUserResponse = userApplicationService.createUser(createUserCommand);
        log.info("User created: {}", createUserResponse);
        return ResponseEntity.ok(createUserResponse);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable("id") UUID id, @RequestBody UpdateUserCommand updateUserCommand) {
        log.info("Updating User with id: {}", id);
        updateUserCommand.setId(id);
        UpdateUserResponse updateUserResponse = userApplicationService.updateUser(updateUserCommand);
        log.info("User updated with: {}", updateUserResponse);
        return ResponseEntity.ok(updateUserResponse);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable("id") UUID id, @RequestBody Optional<DeleteUserCommand> deleteUserCommand) {
        log.info("Deleting User with id: {}", id);
        DeleteUserCommand deleteUserCommandWithId = deleteUserCommand.orElse(DeleteUserCommand.builder().build());
        deleteUserCommandWithId.setId(id);
        DeleteUserResponse deleteUserResponse = userApplicationService.deleteUser(deleteUserCommandWithId);
        log.info("User deleted with id: {}", id);
        return ResponseEntity.ok(deleteUserResponse);
    }

}
