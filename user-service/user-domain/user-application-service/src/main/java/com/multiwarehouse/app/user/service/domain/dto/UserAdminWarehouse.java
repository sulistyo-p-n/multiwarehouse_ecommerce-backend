package com.multiwarehouse.app.user.service.domain.dto;


import com.multiwarehouse.app.domain.valueobject.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UserAdminWarehouse {
    @NotNull(message = "WarehouseId {jakarta.validation.constraints.NotNull.message}")
    private final UUID warehouseId;
}
