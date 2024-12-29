package com.multiwarehouse.app.user.service.domain.dto.create;

import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.user.service.domain.dto.UserAddress;
import com.multiwarehouse.app.user.service.domain.dto.UserAdminWarehouse;
import com.multiwarehouse.app.user.service.domain.dto.UserProfile;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CreateUserCommand {
    @NotNull(message = "Username {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Username {jakarta.validation.constraints.Size.message}")
    private final String username;
    @NotNull(message = "Email {jakarta.validation.constraints.NotNull.message}")
    @Email(message = "Email {jakarta.validation.constraints.Email.message}")
    private final String email;
    @NotNull(message = "Password {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Password {jakarta.validation.constraints.Size.message}")
    private final String password;
    @NotNull(message = "Active {jakarta.validation.constraints.NotNull.message}")
    private final Boolean active;
    @NotNull(message = "Role {jakarta.validation.constraints.NotNull.message}")
    private final UserRole role;

    private final UserAdminWarehouse adminWarehouse;
    private final UserProfile profile;
    private final List<UserAddress> addresses;
}
