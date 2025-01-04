package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationStatus;

import java.util.UUID;

public class StockMutation extends BaseEntity<StockMutationId> {
    private final Inventory sourceInventory;
    private final Inventory targetInventory;
    private final Product product;
    private final int quantity;
    private StockMutationStatus status;

    private StockMutation(Builder builder) {
        super.setId(builder.id);
        sourceInventory = builder.sourceInventory;
        targetInventory = builder.targetInventory;
        product = builder.product;
        quantity = builder.quantity;
        status = builder.status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void validateInitialization() {
        validateInitialId();
    }

    private void validateInitialId() {
        if (getId() != null) throw new InventoryDomainException("StockMutation.Id is not in correct state for initialization!");
    }

    public void initialize() {
        setId(new StockMutationId(UUID.randomUUID()));
        status = StockMutationStatus.PENDING;
    }

    public void validate() {
        validateId();
        validateInventory();
        validateProduct();
        validateQuantity();
        validateStatus();
    }

    private void validateId() {
        if (getId() == null) throw new InventoryDomainException("StockMutation.Id cannot be null!");
    }

    public void validateInventory() {
        sourceInventory.validate();
        targetInventory.validate();
    }

    private void validateProduct() {
        if (product == null) throw new InventoryDomainException("StockMutation.Product cannot be null!");
    }

    public void validateQuantity() {
        if (quantity <= 0) {
            throw new InventoryDomainException("StockMutation.Quantity must be greater than zero!");
        }
    }

    private void validateStatus() {
        if (status == null) throw new InventoryDomainException("StockMutation.Status cannot be null!");
    }

    public void validateSourceInventoryAvailableStock() {
        sourceInventory.validateAvailableStock(product, quantity);
    }

    public void validateStatusApproved() {
        if (status != StockMutationStatus.APPROVED) {
            throw new InventoryDomainException("StockMutation.Status cannot be processed in its current state!");
        }
    }

    public void request() {
        if ((getStatus() == StockMutationStatus.APPROVED) || (getStatus() == StockMutationStatus.REJECTED)) {
            throw new InventoryDomainException("StockMutation.Request cannot be requested in its current state!");
        }
        status = StockMutationStatus.PENDING;
    }

    public void reject() {
        if (getStatus() != StockMutationStatus.PENDING) {
            throw new InventoryDomainException("StockMutation.Request cannot be rejected in its current state!");
        }
        status = StockMutationStatus.REJECTED;
    }

    public void approve() {
        if (getStatus() != StockMutationStatus.PENDING) {
            throw new InventoryDomainException("StockMutation.Request cannot be approved in its current state!");
        }
        status = StockMutationStatus.APPROVED;
    }

    public void transferStock() {
        sourceInventory.transferStock(targetInventory, product, quantity);
    }

    public Inventory getSourceInventory() {
        return sourceInventory;
    }

    public Inventory getTargetInventory() {
        return targetInventory;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public StockMutationStatus getStatus() {
        return status;
    }

    public static final class Builder {
        private StockMutationId id;
        private Inventory sourceInventory;
        private Inventory targetInventory;
        private Product product;
        private int quantity;
        private StockMutationStatus status;

        private Builder() {
        }

        public Builder withId(StockMutationId val) {
            id = val;
            return this;
        }

        public Builder withSourceInventory(Inventory val) {
            sourceInventory = val;
            return this;
        }

        public Builder withTargetInventory(Inventory val) {
            targetInventory = val;
            return this;
        }

        public Builder withProduct(Product val) {
            product = val;
            return this;
        }

        public Builder withQuantity(int val) {
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
