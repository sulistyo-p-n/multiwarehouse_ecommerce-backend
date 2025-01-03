package com.multiwarehouse.app.warehouse.service.domain.dto.update;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UpdateWarehouseResponse {
    private final UUID id;
}
