package com.multiwarehouse.app.user.service.domain.dto;

import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.UserId;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserAddress {
    @NotNull(message = "Street {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Street {jakarta.validation.constraints.Size.message}")
    private final String street;
    @NotNull(message = "City {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "City {jakarta.validation.constraints.Size.message}")
    private final String city;
    @NotNull(message = "PostalCode {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "PostalCode {jakarta.validation.constraints.Size.message}")
    private final String postalCode;
    @NotNull(message = "Active {jakarta.validation.constraints.NotNull.message}")
    private final Boolean active;
}
