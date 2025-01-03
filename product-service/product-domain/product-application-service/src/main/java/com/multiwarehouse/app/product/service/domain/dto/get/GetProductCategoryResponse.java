package com.multiwarehouse.app.product.service.domain.dto.get;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetProductCategoryResponse {
    private final UUID id;
    private final String code;
    private final String name;
    private final String description;
    private final Boolean active;
    private final Boolean isSoftDeleted;
}
