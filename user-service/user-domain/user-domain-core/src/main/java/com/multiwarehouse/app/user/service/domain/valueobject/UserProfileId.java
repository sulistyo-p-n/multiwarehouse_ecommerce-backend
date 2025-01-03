package com.multiwarehouse.app.user.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class UserProfileId extends BaseId<UUID> {
    public UserProfileId(UUID value) {
        super(value);
    }
}
