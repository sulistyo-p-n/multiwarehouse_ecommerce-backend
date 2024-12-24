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
public class GetProductResponse {
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private final UUID id;
    @NotNull(message = "Code {jakarta.validation.constraints.NotNull.message}")
    private final String code;
    @NotNull(message = "Name {jakarta.validation.constraints.NotNull.message}")
    private final String name;
    private final String description;
    @NotNull(message = "Price {jakarta.validation.constraints.NotNull.message}")
    private final BigDecimal price;
    @NotNull(message = "Active {jakarta.validation.constraints.NotNull.message}")
    private final Boolean active;

    @NotNull(message = "Category {jakarta.validation.constraints.NotNull.message}")
    private final GetProductCategoryResponse category;

    @NotNull(message = "Product Images {jakarta.validation.constraints.NotNull.message}")
    private final List<GetProductImageResponse> productImages;
}
