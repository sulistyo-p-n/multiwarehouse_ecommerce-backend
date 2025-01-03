package com.multiwarehouse.app.warehouse.service.domain.dto.update;

import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseAddressCommand;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UpdateWarehouseCommand {
    @Setter
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private UUID id;
    private final String code;
    private final String name;
    private final String description;
    private final Boolean active;

    private final UpdateWarehouseAddressCommand address;
}
