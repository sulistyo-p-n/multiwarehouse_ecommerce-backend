package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.StockId;

public class ProductStock extends AggregateRoot<StockId> {
    private final ProductId productId;
    private final Integer quantity;

    private ProductStock(Builder builder) {
        super.setId(builder.id);
        productId = builder.productId;
        quantity = builder.quantity;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ProductId getProductId() {
        return productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public static final class Builder {
        private StockId id;
        private ProductId productId;
        private Integer quantity;

        private Builder() {
        }

        public Builder withId(StockId val) {
            id = val;
            return this;
        }

        public Builder withProductId(ProductId val) {
            productId = val;
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
