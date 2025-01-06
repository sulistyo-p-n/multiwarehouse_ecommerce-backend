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
    private final UUID id;
    private final String code;
    private final String name;
    private final String description;
    private final BigDecimal price;
    private final Boolean active;

    private final GetProductCategoryResponse category;
    private final List<GetProductImageResponse> images;
    private final int quantity;

    private final Boolean softDeleted;
}
