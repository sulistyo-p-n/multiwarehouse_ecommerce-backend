package com.multiwarehouse.app.inventory.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class InventoryResponse {
    private final UUID id;
    private final Boolean active;
    private final List<InventoryStockResponse> stocks;
}
