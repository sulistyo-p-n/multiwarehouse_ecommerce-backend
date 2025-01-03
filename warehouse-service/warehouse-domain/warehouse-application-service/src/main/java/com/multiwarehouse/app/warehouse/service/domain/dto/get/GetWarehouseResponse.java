package com.multiwarehouse.app.warehouse.service.domain.dto.get;

import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseAddressCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetWarehouseResponse {
    private UUID id;
    private final String code;
    private final String name;
    private final String description;
    private final Boolean active;

    private final GetWarehouseAddressResponse address;

    private final Boolean softDeleted;
}
