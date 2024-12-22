package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.exception.ProductDomainException;

import java.util.UUID;

public class ProductCategory extends BaseEntity<ProductCategoryId> {
    private final String code;
    private final String name;
    private final String desc;
    private final Boolean active;

    public void initializeCategory() {
        setId(new ProductCategoryId(UUID.randomUUID()));
    }

    public void validationInitialCategory() {
        if (getId() != null) {
            throw  new ProductDomainException("Category is not in correct state for initialization");
        }
    }

    private ProductCategory(Builder builder) {
        super.setId(builder.id);
        code = builder.code;
        name = builder.name;
        desc = builder.desc;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Boolean getActive() {
        return active;
    }

    public static final class Builder {
        private ProductCategoryId id;
        private String code;
        private String name;
        private String desc;
        private Boolean active;

        private Builder() {
        }

        public Builder withId(ProductCategoryId val) {
            id = val;
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

        public Builder withDesc(String val) {
            desc = val;
            return this;
        }

        public Builder withActive(Boolean val) {
            active = val;
            return this;
        }

        public ProductCategory build() {
            return new ProductCategory(this);
        }
    }
}
