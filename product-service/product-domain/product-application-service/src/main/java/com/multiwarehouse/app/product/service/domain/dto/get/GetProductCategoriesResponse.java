package com.multiwarehouse.app.product.service.domain.dto.get;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetProductCategoriesResponse {
    @NotNull(message = "Product Categories {jakarta.validation.constraints.NotNull.message}")
    private final List<GetProductCategoryResponse> productCategories;
}
