package com.multiwarehouse.app.inventory.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class RequestStockMutationCommand {
    @NotNull(message = "SourceWarehouseId {jakarta.validation.constraints.NotNull.message}")
    private final UUID sourceWarehouseId;
    @NotNull(message = "TargeteWarehouseId {jakarta.validation.constraints.NotNull.message}")
    private final UUID targetWarehouseId;
    @NotNull(message = "ProductId {jakarta.validation.constraints.NotNull.message}")
    private final UUID productId;
    @NotNull(message = "Quantity {jakarta.validation.constraints.NotNull.message}")
    private final Integer quantity;
}
