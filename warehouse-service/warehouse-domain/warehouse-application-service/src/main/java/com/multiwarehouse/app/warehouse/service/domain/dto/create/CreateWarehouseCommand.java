package com.multiwarehouse.app.warehouse.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateWarehouseCommand {
    @NotNull(message = "Name {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Name {jakarta.validation.constraints.Size.message}")
    private final String name;
    @NotNull(message = "Address Street {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Address Street {jakarta.validation.constraints.Size.message}")
    private final String addressStreet;
    @NotNull(message = "Address City {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Address City {jakarta.validation.constraints.Size.message}")
    private final String addressCity;
    @NotNull(message = "Address PostalCode {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 10, message = "Address PostalCode {jakarta.validation.constraints.Size.message}")
    private final String addressPostalCode;
}
