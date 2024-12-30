package com.multiwarehouse.app.inventory.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class StockMutationId extends BaseId<UUID> {
    public StockMutationId(UUID value) {
        super(value);
    }
}
