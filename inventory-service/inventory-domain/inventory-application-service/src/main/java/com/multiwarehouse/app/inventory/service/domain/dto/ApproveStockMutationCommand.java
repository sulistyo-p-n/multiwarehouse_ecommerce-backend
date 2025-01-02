package com.multiwarehouse.app.inventory.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ApproveStockMutationCommand {
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private final UUID id;
}
