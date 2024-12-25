package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.exception.ProductCategoryDomainException;
import com.multiwarehouse.app.product.service.domain.exception.ProductImageDomainException;
import com.multiwarehouse.app.product.service.domain.valueobject.ProductImageId;

import java.util.UUID;

public class ProductImage extends BaseEntity<ProductImageId> {
    private ProductId productId;
    private String code;
    private String name;
    private String description;
    private final String path;
    private Boolean active;

    public void initialize() {
        setId(new ProductImageId(UUID.randomUUID()));
    }

    public void validateInitialization() {
        validateInitialId();
        validateFields();
    }

    public void validateInitialId() {
        if (getId() != null) {
            throw  new ProductImageDomainException("ProductImage is not in correct state for initialization");
        }
    }

    public void validate() {
        validateId();
        validateFields();
    }

    public void validateId() {
        if (getId() == null ) throw new ProductImageDomainException("ProductImage Id cannot be null");
    }

    public void validateFields() {
        validateProductId();
        validateCode();
        validateName();
        validatePath();
        validateActive();
    }

    public void validateProductId() {
        if (getProductId() == null) throw new ProductImageDomainException("ProductImage ProductId cannot be null");
    }

    private void validateCode() {
        if (getCode() == null || getCode().isEmpty()) throw new ProductImageDomainException("ProductImage Code cannot be empty");
    }

    private void validateName() {
        if (getName() == null || getName().isEmpty()) throw new ProductImageDomainException("ProductImage Name cannot be empty");
    }

    private void validatePath() {
        if (getName() == null || getName().isEmpty()) throw new ProductImageDomainException("ProductImage Path cannot be empty");
    }

    private void validateActive() {
        if (getActive() == null) throw new ProductImageDomainException("ProductImage Active cannot be null");
    }

    public void setProductId(ProductId value) {
        if (value == null) return;
        productId = value;
    }

    public void setCode(String value) {
        if (value == null) return;
        if (value.isEmpty()) throw new ProductImageDomainException("ProductImage Code cannot be empty");
        code = value;
    }

    public void setName(String value) {
        if (value == null) return;
        if (value.isEmpty()) throw new ProductImageDomainException("ProductImage Name cannot be empty");
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

    private ProductImage(Builder builder) {
        super.setId(builder.id);
        productId = builder.productId;
        code = builder.code;
        name = builder.name;
        description = builder.description;
        path = builder.path;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ProductId getProductId() {
        return productId;
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

    public String getPath() {
        return path;
    }

    public Boolean getActive() {
        return active;
    }

    public static final class Builder {
        private ProductImageId id;
        private ProductId productId;
        private String code;
        private String name;
        private String description;
        private String path;
        private Boolean active;

        private Builder() {
        }

        public Builder withId(ProductImageId val) {
            id = val;
            return this;
        }

        public Builder withProductId(ProductId val) {
            productId = val;
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

        public Builder withPath(String val) {
            path = val;
            return this;
        }

        public Builder withActive(Boolean val) {
            active = val;
            return this;
        }

        public ProductImage build() {
            return new ProductImage(this);
        }
    }
}
