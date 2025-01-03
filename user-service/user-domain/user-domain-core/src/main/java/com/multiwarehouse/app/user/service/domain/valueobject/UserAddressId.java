package com.multiwarehouse.app.user.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class UserAddressId extends BaseId<UUID> {
    public UserAddressId(UUID value) {
        super(value);
    }
}
