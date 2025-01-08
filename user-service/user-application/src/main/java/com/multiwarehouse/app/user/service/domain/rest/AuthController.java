package com.multiwarehouse.app.user.service.domain.rest;

import com.multiwarehouse.app.user.service.domain.dto.login.LoginUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.login.LoginUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.register.RegisterUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.register.RegisterUserResponse;
import com.multiwarehouse.app.user.service.domain.ports.input.service.UserApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/auth", produces = "application/vnd.api.v1+json")
public class AuthController {
    private final UserApplicationService userApplicationService;

    public AuthController(UserApplicationService userApplicationService) {
        this.userApplicationService = userApplicationService;
    }

    @PostMapping(path = "login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserCommand loginUserCommand) {
        log.info("Login User: {}", loginUserCommand);
        LoginUserResponse loginUserResponse = userApplicationService.loginUser(loginUserCommand);
        log.info("User is in: {}", loginUserResponse);
        return ResponseEntity.ok(loginUserResponse);
    }

    @PostMapping(path = "register")
    public ResponseEntity<RegisterUserResponse> register(@RequestBody RegisterUserCommand registerUserCommand) {
        log.info("Register User: {}", registerUserCommand);
        RegisterUserResponse registerUserResponse = userApplicationService.registerUser(registerUserCommand);
        log.info("User is registered: {}", registerUserResponse);
        return ResponseEntity.ok(registerUserResponse);
    }
}
