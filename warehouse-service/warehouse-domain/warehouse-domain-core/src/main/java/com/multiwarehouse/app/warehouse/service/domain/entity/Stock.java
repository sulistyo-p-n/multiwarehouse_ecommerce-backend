package com.multiwarehouse.app.warehouse.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.StockId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;

public class Stock extends AggregateRoot<StockId> {
    private final WarehouseId warehouseId;
    private final ProductId productId;
    private final int quantity;

    private Stock(Builder builder) {
        super.setId(builder.id);
        warehouseId = builder.warehouseId;
        productId = builder.productId;
        quantity = builder.quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public static final class Builder {
        private StockId id;
        private WarehouseId warehouseId;
        private ProductId productId;
        private int quantity;

        private Builder() {
        }

        public Builder withId(StockId val) {
            id = val;
            return this;
        }

        public Builder withWarehouseId(WarehouseId val) {
            warehouseId = val;
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

        public Stock build() {
            return new Stock(this);
        }
    }
}
