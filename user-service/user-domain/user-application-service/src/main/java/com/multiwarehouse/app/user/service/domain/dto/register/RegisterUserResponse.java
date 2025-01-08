package com.multiwarehouse.app.user.service.domain.dto.register;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class RegisterUserResponse {
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private final UUID id;
}