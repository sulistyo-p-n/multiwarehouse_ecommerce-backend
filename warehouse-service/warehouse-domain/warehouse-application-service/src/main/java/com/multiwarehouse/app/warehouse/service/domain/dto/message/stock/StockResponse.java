package com.multiwarehouse.app.warehouse.service.domain.dto.message.stock;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class StockResponse {
    private final String id;
    private final String warehouseId;
    private final String productId;
    private final int quantity;
    private final Instant createdAt;
}
