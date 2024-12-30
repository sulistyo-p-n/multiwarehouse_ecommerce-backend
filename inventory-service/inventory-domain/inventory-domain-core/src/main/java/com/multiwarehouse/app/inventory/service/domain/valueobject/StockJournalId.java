package com.multiwarehouse.app.inventory.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class StockJournalId extends BaseId<UUID> {
    public StockJournalId(UUID value) {
        super(value);
    }
}
