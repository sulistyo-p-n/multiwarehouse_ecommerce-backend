package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.StockId;

public class ProductStock extends BaseEntity<ProductId> {
    private final Integer quantity;

    private ProductStock(Builder builder) {
        super.setId(builder.id);
        quantity = builder.quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public static final class Builder {
        private ProductId id;
        private Integer quantity;

        private Builder() {
        }

        public Builder withId(ProductId val) {
            id = val;
            return this;
        }

        public Builder withQuantity(Integer val) {
            quantity = val;
            return this;
        }

        public ProductStock build() {
            return new ProductStock(this);
        }
    }
}
