package com.multiwarehouse.app.inventory.service.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class AddStockToInventoryCommand {
    @Setter
    @NotNull(message = "WarehouseId {jakarta.validation.constraints.NotNull.message}")
    private UUID warehouseId;
    @NotNull(message = "ProductId {jakarta.validation.constraints.NotNull.message}")
    private final UUID productId;
    @NotNull(message = "Quantity {jakarta.validation.constraints.NotNull.message}")
    private final Integer quantity;
}
