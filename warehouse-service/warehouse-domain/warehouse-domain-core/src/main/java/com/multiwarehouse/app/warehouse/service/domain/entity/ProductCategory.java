package com.multiwarehouse.app.warehouse.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;

public class ProductCategory extends BaseEntity<ProductCategoryId> {
    private final String name;
    private final String desc;

    private ProductCategory(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        desc = builder.desc;
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

    public static final class Builder {
        private ProductCategoryId id;
        private String name;
        private String desc;

        private Builder() {
        }

        public Builder withId(ProductCategoryId val) {
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

        public ProductCategory build() {
            return new ProductCategory(this);
        }
    }
}
