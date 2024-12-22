package com.multiwarehouse.app.product.service.domain.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UpdateProductCommand {
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private final UUID id;
    private final String code;
    private final String name;
    private final String desc;
    private final BigDecimal price;
    private final UUID categoryId;
    private final Boolean active;
}
