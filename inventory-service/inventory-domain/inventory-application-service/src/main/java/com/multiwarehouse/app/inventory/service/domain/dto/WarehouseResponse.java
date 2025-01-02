package com.multiwarehouse.app.inventory.service.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class WarehouseResponse {
    private final UUID id;
    private final String code;
    private final String name;
    private final Boolean active;
}
