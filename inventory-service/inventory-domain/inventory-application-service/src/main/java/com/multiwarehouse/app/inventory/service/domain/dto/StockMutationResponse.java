package com.multiwarehouse.app.inventory.service.domain.dto;

import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class StockMutationResponse {
    private final UUID id;
    private final WarehouseResponse sourceWarehouse;
    private final WarehouseResponse targetWarehouse;
    private final ProductResponse product;
    private final int quantity;
    private final StockMutationStatus status;
}
