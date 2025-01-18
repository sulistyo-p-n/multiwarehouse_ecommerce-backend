package com.multiwarehouse.app.user.service.domain.dto.login;

import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.user.service.domain.dto.UserAddress;
import com.multiwarehouse.app.user.service.domain.dto.UserAdminWarehouse;
import com.multiwarehouse.app.user.service.domain.dto.UserProfile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class LoginUserResponse {
    private final String token;
    private final UUID id;
    private final String username;
    private final String email;
    private final UserRole role;

    private final UserAdminWarehouse adminWarehouse;
    private final UserProfile profile;
    private final List<UserAddress> addresses;
}
