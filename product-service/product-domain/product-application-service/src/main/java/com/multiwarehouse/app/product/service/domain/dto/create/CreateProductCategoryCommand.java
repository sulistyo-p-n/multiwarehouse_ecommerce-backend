package com.multiwarehouse.app.product.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateProductCategoryCommand {
    @NotNull(message = "Code {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Code {jakarta.validation.constraints.Size.message}")
    private final String code;
    @NotNull(message = "Name {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Name {jakarta.validation.constraints.Size.message}")
    private final String name;
    private final String description;
    @NotNull(message = "Active {jakarta.validation.constraints.NotNull.message}")
    private final Boolean active;
}
