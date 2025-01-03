package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;
import com.multiwarehouse.app.inventory.service.domain.valueobject.ProductStockId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalType;

import java.time.Instant;
import java.util.UUID;

public class StockJournal extends BaseEntity<StockJournalId> {
    private ProductStockId productStockId;
    private final int quantity;
    private final StockJournalType type;
    private final Instant createdAt;

    private StockJournal(Builder builder) {
        super.setId(builder.id);
        productStockId = builder.productStockId;
        quantity = builder.quantity;
        type = builder.type;
        createdAt = builder.createdAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initialize(ProductStockId productStockId) {
        this.productStockId = productStockId;
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
        if (productStockId == null) throw new InventoryDomainException("StockJournal.ProductStockId cannot be null!");
    }

    private void validateQuantity() {
        if (quantity == 0) throw new InventoryDomainException("StockJournal.Quantity cannot be zero!");
    }

    private void validateType() {
        if (type == null) throw new InventoryDomainException("StockJournal.Type cannot be null!");
    }

    public ProductStockId getProductStockId() {
        return productStockId;
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

    public void setProductStockId(ProductStockId productStockId) {
        this.productStockId = productStockId;
    }

    public static final class Builder {
        private StockJournalId id;
        private ProductStockId productStockId;
        private int quantity;
        private StockJournalType type;
        private Instant createdAt;

        private Builder() {
        }

        public Builder withId(StockJournalId val) {
            id = val;
            return this;
        }

        public Builder withProductStockId(ProductStockId val) {
            productStockId = val;
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
