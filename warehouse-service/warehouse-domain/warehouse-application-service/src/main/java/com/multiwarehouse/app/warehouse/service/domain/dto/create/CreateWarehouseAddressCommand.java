package com.multiwarehouse.app.warehouse.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateWarehouseAddressCommand {
    @NotNull(message = "Street {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Address Street {jakarta.validation.constraints.Size.message}")
    private final String street;
    @NotNull(message = "City {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Address City {jakarta.validation.constraints.Size.message}")
    private final String city;
    @NotNull(message = "PostalCode {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 10, message = "Address PostalCode {jakarta.validation.constraints.Size.message}")
    private final String postalCode;
    @NotNull(message = "Latitude {jakarta.validation.constraints.NotNull.message}")
    private final Float latitude;
    @NotNull(message = "Longitude {jakarta.validation.constraints.NotNull.message}")
    private final Float longitude;
}
