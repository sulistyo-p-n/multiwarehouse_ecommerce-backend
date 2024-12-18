package com.multiwarehouse.app.warehouse.service.domain.dto.message.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class ProductResponse {
    private final String id;
    private final String name;
    private final String desc;
    private final BigDecimal price;
    private final Instant createdAt;
}
