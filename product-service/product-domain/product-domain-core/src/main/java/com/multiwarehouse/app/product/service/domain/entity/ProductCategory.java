package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.exception.ProductCategoryDomainException;

import java.util.UUID;

public class ProductCategory extends BaseEntity<ProductCategoryId> {
    private String code;
    private String name;
    private String description;
    private Boolean active;

    private final Boolean softDeleted;

    public void initialize() {
        setId(new ProductCategoryId(UUID.randomUUID()));
    }

    public void validateInitialization() {
        validateInitialId();
        validateFields();
    }

    public void validateInitialId() {
        if (getId() != null) {
            throw new ProductCategoryDomainException("Product Category is not in correct state for initialization");
        }
    }

    public void validate() {
        validateId();
        validateFields();
    }

    public void validateId() {
        if (getId() == null ) throw new ProductCategoryDomainException("ProductCategory Id cannot be null");
    }

    public void validateFields() {
        validateCode();
        validateName();
        validateActive();
    }

    private void validateCode() {
        if (code == null || getCode().isEmpty()) throw new ProductCategoryDomainException("ProductCategory Code cannot be empty");
    }

    private void validateName() {
        if (name == null || getName().isEmpty()) throw new ProductCategoryDomainException("ProductCategory Name cannot be empty");
    }

    private void validateActive() {
        if (active == null) throw new ProductCategoryDomainException("ProductCategory Active cannot be null");
    }

    public void setCode(String value) {
        if (value == null) return;
        if (value.isEmpty()) throw new ProductCategoryDomainException("ProductCategory Code cannot be empty");
        code = value;
    }

    public void setName(String value) {
        if (value == null) return;
        if (value.isEmpty()) throw new ProductCategoryDomainException("ProductCategory Name cannot be empty");
        name = value;
    }

    public void setDescription(String value) {
        if (value == null) return;
        description = value;
    }

    public void setActive(Boolean value) {
        if (value == null) return;
        active = value;
    }

    private ProductCategory(Builder builder) {
        super.setId(builder.id);
        code = builder.code;
        name = builder.name;
        description = builder.description;
        active = builder.active;
        softDeleted = builder.softDeleted;
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

    public String getDescription() {
        return description;
    }

    public Boolean isActive() {
        return active;
    }

    public Boolean isSoftDeleted() {
        return softDeleted;
    }

    public static final class Builder {
        private ProductCategoryId id;
        private String code;
        private String name;
        private String description;
        private Boolean active;
        private Boolean softDeleted;

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

        public Builder withDescription(String val) {
            description = val;
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

        public ProductCategory build() {
            return new ProductCategory(this);
        }
    }
}
