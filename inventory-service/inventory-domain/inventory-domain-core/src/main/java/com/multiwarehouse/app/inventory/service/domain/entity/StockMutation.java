package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationStatus;

public class StockMutation extends BaseEntity<StockMutationId> {
    private final Warehouse sourceWarehouse;
    private final Warehouse targetWarehouse;
    private final Product product;
    private final Integer quantity;
    private StockMutationStatus status;

    private StockMutation(Builder builder) {
        super.setId(builder.id);
        sourceWarehouse = builder.sourceWarehouse;
        targetWarehouse = builder.targetWarehouse;
        product = builder.product;
        quantity = builder.quantity;
        status = builder.status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Warehouse getSourceWarehouse() {
        return sourceWarehouse;
    }

    public Warehouse getTargetWarehouse() {
        return targetWarehouse;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public StockMutationStatus getStatus() {
        return status;
    }

    public void approve() {
        if (getStatus() != StockMutationStatus.PENDING) {
            throw new InventoryDomainException("Transfer request cannot be approved in its current state!");
        }
        status = StockMutationStatus.APPROVED;
    }

    public void reject() {
        if (getStatus() != StockMutationStatus.PENDING) {
            throw new InventoryDomainException("Transfer request cannot be approved in its current state!");
        }
        status = StockMutationStatus.REJECTED;
    }

    public static final class Builder {
        private StockMutationId id;
        private Warehouse sourceWarehouse;
        private Warehouse targetWarehouse;
        private Product product;
        private Integer quantity;
        private StockMutationStatus status;

        private Builder() {
        }

        public Builder withId(StockMutationId val) {
            id = val;
            return this;
        }

        public Builder withSourceWarehouse(Warehouse val) {
            sourceWarehouse = val;
            return this;
        }

        public Builder withTargetWarehouse(Warehouse val) {
            targetWarehouse = val;
            return this;
        }

        public Builder withProduct(Product val) {
            product = val;
            return this;
        }

        public Builder withQuantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder withStatus(StockMutationStatus val) {
            status = val;
            return this;
        }

        public StockMutation build() {
            return new StockMutation(this);
        }
    }
}
