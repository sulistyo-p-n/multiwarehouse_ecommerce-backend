package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;
import com.multiwarehouse.app.inventory.service.domain.valueobject.InventoryStockId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalType;

import java.time.Instant;
import java.util.UUID;

public class StockJournal extends BaseEntity<StockJournalId> {
    private InventoryStockId inventoryStockId;
    private final int quantity;
    private final StockJournalType type;
    private final Instant createdAt;

    private StockJournal(Builder builder) {
        super.setId(builder.id);
        inventoryStockId = builder.inventoryStockId;
        quantity = builder.quantity;
        type = builder.type;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initialize(InventoryStockId inventoryStockId) {
        this.inventoryStockId = inventoryStockId;
        setId(new StockJournalId(UUID.randomUUID()));
    }

    public void validate() {
        validateId();
        validateProductStockId();
        validateQuantity();
        validateType();
    }

    private void validateId() {
        if (getId() == null) throw new InventoryDomainException("StockJournal.Id cannot be null!");
    }

    private void validateProductStockId() {
        if (inventoryStockId == null) throw new InventoryDomainException("StockJournal.ProductStockId cannot be null!");
    }

    private void validateQuantity() {
        if (quantity == 0) throw new InventoryDomainException("StockJournal.Quantity cannot be zero!");
    }

    private void validateType() {
        if (type == null) throw new InventoryDomainException("StockJournal.Type cannot be null!");
    }

    public InventoryStockId getProductStockId() {
        return inventoryStockId;
    }

    public int getQuantity() {
        return quantity;
    }

    public StockJournalType getType() {
        return type;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setInventoryStockId(InventoryStockId inventoryStockId) {
        this.inventoryStockId = inventoryStockId;
    }

    public static final class Builder {
        private StockJournalId id;
        private InventoryStockId inventoryStockId;
        private int quantity;
        private StockJournalType type;
        private Instant createdAt;

        private Builder() {
        }

        public Builder withId(StockJournalId val) {
            id = val;
            return this;
        }

        public Builder withInventoryStockId(InventoryStockId val) {
            inventoryStockId = val;
            return this;
        }

        public Builder withQuantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder withType(StockJournalType val) {
            type = val;
            return this;
        }

        public Builder withCreatedAt(Instant val) {
            createdAt = val;
            return this;
        }

        public StockJournal build() {
            return new StockJournal(this);
        }
    }
}
