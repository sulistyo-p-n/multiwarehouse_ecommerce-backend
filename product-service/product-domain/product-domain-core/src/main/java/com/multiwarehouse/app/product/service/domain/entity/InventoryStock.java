package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.valueobject.InventoryStockId;

public class InventoryStock extends BaseEntity<InventoryStockId> {
    private InventoryId inventoryId;
    private final ProductId productId;
    private final int quantity;

    private InventoryStock(Builder builder) {
        super.setId(builder.id);
        inventoryId = builder.inventoryId;
        productId = builder.productId;
        quantity = builder.quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void setInventoryId(InventoryId inventoryId) {
        this.inventoryId = inventoryId;
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public static final class Builder {
        private InventoryStockId id;
        private InventoryId inventoryId;
        private ProductId productId;
        private int quantity;

        private Builder() {
        }

        public Builder withId(InventoryStockId val) {
            id = val;
            return this;
        }

        public Builder withInventoryId(InventoryId val) {
            inventoryId = val;
            return this;
        }

        public Builder withProductId(ProductId val) {
            productId = val;
            return this;
        }

        public Builder withQuantity(int val) {
            quantity = val;
            return this;
        }

        public InventoryStock build() {
            return new InventoryStock(this);
        }
    }
}
