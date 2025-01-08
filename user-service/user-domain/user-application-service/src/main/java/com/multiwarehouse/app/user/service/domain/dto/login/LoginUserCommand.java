package com.multiwarehouse.app.user.service.domain.dto.login;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginUserCommand {
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private final String email;
    @NotNull(message = "Password {jakarta.validation.constraints.NotNull.message}")
    private final String password;
}