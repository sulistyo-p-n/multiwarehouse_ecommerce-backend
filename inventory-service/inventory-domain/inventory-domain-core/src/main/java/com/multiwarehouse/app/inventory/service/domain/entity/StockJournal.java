package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.inventory.service.domain.valueobject.ProductStockId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalType;

public class StockJournal extends BaseEntity<StockJournalId> {
    private final ProductStockId productStockId;
    private final Integer quantity;
    private final StockJournalType type;

    private StockJournal(Builder builder) {
        super.setId(builder.id);
        productStockId = builder.productStockId;
        quantity = builder.quantity;
        type = builder.type;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ProductStockId getProductStockId() {
        return productStockId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public StockJournalType getType() {
        return type;
    }

    public static final class Builder {
        private StockJournalId id;
        private ProductStockId productStockId;
        private Integer quantity;
        private StockJournalType type;

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

        public StockJournal build() {
            return new StockJournal(this);
        }
    }
}
