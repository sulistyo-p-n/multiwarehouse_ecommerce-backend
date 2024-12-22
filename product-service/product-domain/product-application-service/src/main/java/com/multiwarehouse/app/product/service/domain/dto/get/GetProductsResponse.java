package com.multiwarehouse.app.product.service.domain.dto.get;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductImageCommand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetProductsResponse {
    @NotNull(message = "Products {jakarta.validation.constraints.NotNull.message}")
    private final List<GetProductResponse> products;
}
