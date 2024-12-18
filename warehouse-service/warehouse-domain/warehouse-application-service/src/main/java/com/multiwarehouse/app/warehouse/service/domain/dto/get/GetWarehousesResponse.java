package com.multiwarehouse.app.warehouse.service.domain.dto.get;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class GetWarehousesResponse {
    @NotNull
    private final List<GetWarehouseResponse> warehouses;
}
