package com.multiwarehouse.app.product.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateProductImageCommand {
    @NotNull(message = "Name {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Name {jakarta.validation.constraints.Size.message}")
    private final String name;
    private final String description;
    @NotNull(message = "Path {jakarta.validation.constraints.NotNull.message}")
    private final String path;
    @NotNull(message = "Front {jakarta.validation.constraints.NotNull.message}")
    private final Boolean front;
    @NotNull(message = "Active {jakarta.validation.constraints.NotNull.message}")
    private final Boolean active;
}