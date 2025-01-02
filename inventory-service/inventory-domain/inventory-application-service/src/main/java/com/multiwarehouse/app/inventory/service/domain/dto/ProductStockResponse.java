package com.multiwarehouse.app.inventory.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ProductStockResponse {
    private final UUID id;
    private final ProductResponse product;
    private final List<StockJournalResponse> stockJournals;
}
