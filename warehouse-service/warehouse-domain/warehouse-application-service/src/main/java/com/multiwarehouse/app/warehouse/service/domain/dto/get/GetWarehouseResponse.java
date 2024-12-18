package com.multiwarehouse.app.warehouse.service.domain.dto.get;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetWarehouseResponse {
    @NotNull
    private final UUID id;
    @NotNull
    private final String name;
    @NotNull
    private final String addressStreet;
    @NotNull
    private final String addressCity;
    @NotNull
    private final String addressPostalCode;
}
