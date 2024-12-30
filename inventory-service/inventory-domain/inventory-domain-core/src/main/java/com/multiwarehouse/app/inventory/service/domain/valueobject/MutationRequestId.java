package com.multiwarehouse.app.inventory.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class MutationRequestId extends BaseId<UUID> {
    public MutationRequestId(UUID value) {
        super(value);
    }
}
