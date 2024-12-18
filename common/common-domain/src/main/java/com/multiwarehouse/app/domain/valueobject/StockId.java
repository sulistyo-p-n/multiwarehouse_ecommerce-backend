package com.multiwarehouse.app.domain.valueobject;

import java.util.UUID;

public class StockId extends BaseId<UUID> {

    public StockId(UUID value) {
        super(value);
    }
}
