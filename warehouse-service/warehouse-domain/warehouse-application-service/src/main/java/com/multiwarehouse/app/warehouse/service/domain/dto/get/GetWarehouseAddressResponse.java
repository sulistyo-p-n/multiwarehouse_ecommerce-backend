package com.multiwarehouse.app.warehouse.service.domain.dto.get;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetWarehouseAddressResponse {
    private final String street;
    private final String city;
    private final String postalCode;
    private final Float latitude;
    private final Float longitude;
}
