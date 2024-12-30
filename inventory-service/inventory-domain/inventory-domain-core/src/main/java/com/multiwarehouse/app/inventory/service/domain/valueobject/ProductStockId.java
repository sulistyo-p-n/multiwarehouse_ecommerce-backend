package com.multiwarehouse.app.inventory.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class ProductStockId extends BaseId<UUID> {
    public ProductStockId(UUID value) {
        super(value);
    }
}
