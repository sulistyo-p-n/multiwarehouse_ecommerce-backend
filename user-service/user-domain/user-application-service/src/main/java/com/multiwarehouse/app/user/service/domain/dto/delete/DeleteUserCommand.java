package com.multiwarehouse.app.user.service.domain.dto.delete;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class DeleteUserCommand {
    @Setter
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private UUID id;
    private final Boolean forceDelete;

    public boolean isForceDelete() {
        return (getForceDelete() != null && getForceDelete());
    }
}
