package com.multiwarehouse.app.user.service.domain.valuobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class AdminId extends BaseId<UUID> {
    public AdminId(UUID value) {
        super(value);
    }
}
