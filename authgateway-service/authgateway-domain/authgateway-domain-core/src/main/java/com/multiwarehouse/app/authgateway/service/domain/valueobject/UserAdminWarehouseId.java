package com.multiwarehouse.app.authgateway.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class UserAdminWarehouseId extends BaseId<UUID> {
    public UserAdminWarehouseId(UUID value) {
        super(value);
    }
}
