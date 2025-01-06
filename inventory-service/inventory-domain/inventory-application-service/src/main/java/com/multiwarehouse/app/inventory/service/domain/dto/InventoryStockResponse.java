package com.multiwarehouse.app.inventory.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class InventoryStockResponse {
    private final UUID id;
    private final ProductResponse product;
    private final Integer quantity;
    private final List<StockJournalResponse> journals;
}
