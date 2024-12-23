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
public class CreateProductCommand {
    @NotNull(message = "Code {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Code {jakarta.validation.constraints.Size.message}")
    private final String code;
    @NotNull(message = "Name {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Name {jakarta.validation.constraints.Size.message}")
    private final String name;
    @NotNull(message = "Description {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Description {jakarta.validation.constraints.Size.message}")
    private final String description;
    @NotNull(message = "Price {jakarta.validation.constraints.NotNull.message}")
    private final BigDecimal price;
    @NotNull(message = "CategoryId {jakarta.validation.constraints.NotNull.message}")
    private final UUID categoryId;
    @NotNull(message = "Active {jakarta.validation.constraints.NotNull.message}")
    private final Boolean active;

    private final List<CreateProductImageCommand> createProductImageCommands;
}