package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;

public class Product extends AggregateRoot<ProductId> {
    private final ProductCategoryId productCategoryId;
    private final String code;
    private final String name;
    private final Money price;
    private final Boolean active;

    private final Boolean softDeleted;

    private Product(Builder builder) {
        super.setId(builder.id);
        productCategoryId = builder.productCategoryId;
        code = builder.code;
        name = builder.name;
        price = builder.price;
        active = builder.active;
        softDeleted = builder.softDeleted;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ProductCategoryId getProductCategoryId() {
        return productCategoryId;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public Boolean isActive() {
        return active;
    }

    public Boolean isSoftDeleted() {
        return softDeleted;
    }

    public static final class Builder {
        private ProductId id;
        private ProductCategoryId productCategoryId;
        private String code;
        private String name;
        private Money price;
        private Boolean active;
        private Boolean softDeleted;

        private Builder() {
        }

        public Builder withId(ProductId val) {
            id = val;
            return this;
        }

        public Builder withProductCategoryId(ProductCategoryId val) {
            productCategoryId = val;
            return this;
        }

        public Builder withCode(String val) {
            code = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withPrice(Money val) {
            price = val;
            return this;
        }

        public Builder withActive(Boolean val) {
            active = val;
            return this;
        }

        public Builder withSoftDeleted(Boolean val) {
            softDeleted = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
