package com.multiwarehouse.app.warehouse.service.domain.dto.update;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UpdateWarehouseCommand {
    @NotNull
    private final UUID id;
    @NotNull
    @Max(value = 50)
    private final String name;
    @NotNull
    @Max(value = 50)
    private final String addressStreet;
    @NotNull
    @Max(value = 50)
    private final String addressCity;
    @NotNull
    @Max(value = 10)
    private final String addressPostalCode;
}
