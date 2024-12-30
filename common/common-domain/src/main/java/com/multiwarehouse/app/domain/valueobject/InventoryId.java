package com.multiwarehouse.app.domain.valueobject;

import java.util.UUID;

public class InventoryId extends BaseId<UUID> {

    public InventoryId(UUID value) {
        super(value);
    }
}
