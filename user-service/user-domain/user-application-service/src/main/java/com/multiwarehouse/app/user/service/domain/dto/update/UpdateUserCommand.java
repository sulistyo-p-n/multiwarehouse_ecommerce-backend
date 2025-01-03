package com.multiwarehouse.app.user.service.domain.dto.update;

import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.user.service.domain.dto.UserAddress;
import com.multiwarehouse.app.user.service.domain.dto.UserAdminWarehouse;
import com.multiwarehouse.app.user.service.domain.dto.UserProfile;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class UpdateUserCommand {
    @Setter
    @NotNull(message = "Id {jakarta.validation.constraints.NotNull.message}")
    private UUID id;
    private final String username;
    private final String email;
    private final String password;
    private final Boolean active;
    private final UserRole role;

    private final UserAdminWarehouse adminWarehouse;
    private final UserProfile profile;
    private final List<UserAddress> addresses;
}
