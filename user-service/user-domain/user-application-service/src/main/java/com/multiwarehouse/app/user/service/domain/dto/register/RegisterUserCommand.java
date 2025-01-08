package com.multiwarehouse.app.user.service.domain.dto.register;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RegisterUserCommand {
    @NotNull(message = "Username {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Username {jakarta.validation.constraints.Size.message}")
    private final String username;
    @NotNull(message = "Email {jakarta.validation.constraints.NotNull.message}")
    @Email(message = "Email {jakarta.validation.constraints.Email.message}")
    private final String email;
    @NotNull(message = "Password {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Password {jakarta.validation.constraints.Size.message}")
    private final String password;
}
