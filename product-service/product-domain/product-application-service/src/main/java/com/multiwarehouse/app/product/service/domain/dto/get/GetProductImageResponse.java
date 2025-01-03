package com.multiwarehouse.app.product.service.domain.dto.get;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetProductImageResponse {
    private final UUID id;
    private final String name;
    private final String description;
    private final String path;
    private final Boolean front;
    private final Boolean active;
}
