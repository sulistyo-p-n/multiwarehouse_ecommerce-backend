package com.multiwarehouse.app.warehouse.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductId;

public class Product extends AggregateRoot<ProductId> {
    private final String name;
    private final String desc;
    private final Money price;
    private final ProductCategory productCategory;
    private final Stock stock;

    private Product(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        desc = builder.desc;
        price = builder.price;
        productCategory = builder.productCategory;
        stock = builder.stock;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Money getPrice() {
        return price;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public Stock getStock() {
        return stock;
    }

    public static final class Builder {
        private ProductId id;
        private String name;
        private String desc;
        private Money price;
        private ProductCategory productCategory;
        private Stock stock;

        private Builder() {
        }

        public Builder withId(ProductId val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withDesc(String val) {
            desc = val;
            return this;
        }

        public Builder withPrice(Money val) {
            price = val;
            return this;
        }

        public Builder withProductCategory(ProductCategory val) {
            productCategory = val;
            return this;
        }

        public Builder withStock(Stock val) {
            stock = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
