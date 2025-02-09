package com.multiwarehouse.app.product.service.domain.dto.get;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetProductCategoryCommand {
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private final UUID id;
}
