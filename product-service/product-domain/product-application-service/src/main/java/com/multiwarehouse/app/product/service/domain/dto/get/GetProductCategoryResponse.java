package com.multiwarehouse.app.product.service.domain.dto.get;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetProductCategoryResponse {
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private final UUID id;
    @NotNull(message = "Code {jakarta.validation.constraints.NotNull.message}")
    private final String code;
    @NotNull(message = "Name {jakarta.validation.constraints.NotNull.message}")
    private final String name;
    @NotNull(message = "Description {jakarta.validation.constraints.NotNull.message}")
    private final String description;
    @NotNull(message = "Active {jakarta.validation.constraints.NotNull.message}")
    private final Boolean active;
}
