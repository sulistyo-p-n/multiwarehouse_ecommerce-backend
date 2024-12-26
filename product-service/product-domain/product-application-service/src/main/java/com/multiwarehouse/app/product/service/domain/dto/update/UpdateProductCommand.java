package com.multiwarehouse.app.product.service.domain.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UpdateProductCommand {
    @Setter
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private UUID id;
    private final String code;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final UUID categoryId;
    private final Boolean active;

    private final List<UpdateProductImageCommand> images;
}
