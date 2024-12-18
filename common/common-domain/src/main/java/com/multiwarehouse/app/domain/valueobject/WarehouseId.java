package com.multiwarehouse.app.domain.valueobject;

import java.util.UUID;

public class WarehouseId extends BaseId<UUID> {

    public WarehouseId(UUID value) {
        super(value);
    }
}
