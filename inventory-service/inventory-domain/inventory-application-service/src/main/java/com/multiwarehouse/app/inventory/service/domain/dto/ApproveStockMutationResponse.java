package com.multiwarehouse.app.inventory.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class ApproveStockMutationResponse {
    private final UUID id;
}
