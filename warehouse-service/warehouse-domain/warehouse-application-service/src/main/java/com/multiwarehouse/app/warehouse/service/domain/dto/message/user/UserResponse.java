package com.multiwarehouse.app.warehouse.service.domain.dto.message.user;

import com.multiwarehouse.app.domain.valueobject.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {
    private final String id;
    private final UserRole role;
    private final String warehouseId;
    private final Instant createdAt;
}
