package com.multiwarehouse.app.inventory.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class InventoryStockId extends BaseId<UUID> {
    public InventoryStockId(UUID value) {
        super(value);
    }
}
