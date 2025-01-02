package com.multiwarehouse.app.inventory.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponse {
    private final UUID id;
    private final UUID categoryId;
    private final String code;
    private final String name;
    private final BigDecimal price;
    private final Boolean active;
}
