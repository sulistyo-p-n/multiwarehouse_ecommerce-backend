package com.multiwarehouse.app.inventory.service.domain.dto;

import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class StockJournalResponse {
    private final int quantity;
    private final StockJournalType type;
    private final Instant createdAt;
}
