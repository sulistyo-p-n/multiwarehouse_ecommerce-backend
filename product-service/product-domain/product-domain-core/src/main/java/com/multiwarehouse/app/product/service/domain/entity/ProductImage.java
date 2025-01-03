package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.exception.ProductDomainException;
import com.multiwarehouse.app.product.service.domain.valueobject.ProductImageId;

import java.util.UUID;

public class ProductImage extends BaseEntity<ProductImageId> {
    private ProductId productId;
    private final String name;
    private final String description;
    private final String path;
    private final Boolean front;
    private final Boolean active;

    public void initialize() {
        setId(new ProductImageId(UUID.randomUUID()));
    }

    public void validateInitialization() {
        validateInitialId();
        validateFields();
    }

    public void validateInitialId() {
        if (getId() != null) {
            throw  new ProductDomainException("ProductImage is not in correct state for initialization");
        }
    }

    public void validate() {
        validateId();
        validateFields();
    }

    public void validateId() {
        if (getId() == null ) throw new ProductDomainException("ProductImage Id cannot be null");
    }

    public void validateFields() {
        validateProductId();
        validateName();
        validatePath();
        validateFront();
        validateActive();
    }

    public void validateProductId() {
        if (productId == null) throw new ProductDomainException("ProductImage ProductId cannot be null");
    }

    private void validateName() {
        if (name == null || getName().isEmpty()) throw new ProductDomainException("ProductImage Name cannot be empty");
    }

    private void validatePath() {
        if (path == null || getName().isEmpty()) throw new ProductDomainException("ProductImage Path cannot be empty");
    }

    private void validateFront() {
        if (front == null) throw new ProductDomainException("ProductImage Front cannot be null");
    }

    private void validateActive() {
        if (active == null) throw new ProductDomainException("ProductImage Active cannot be null");
    }

    public void setProductId(ProductId value) {
        if (value == null) return;
        productId = value;
    }

    private ProductImage(Builder builder) {
        super.setId(builder.id);
        productId = builder.productId;
        name = builder.name;
        description = builder.description;
        path = builder.path;
        front = builder.front;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public ProductId getProductId() {
        return productId;
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

    public Boolean isFront() {
        return front;
    }

    public Boolean isActive() {
        return active;
    }

    public static final class Builder {
        private ProductImageId id;
        private ProductId productId;
        private String name;
        private String description;
        private String path;
        private Boolean front;
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

        public Builder withFront(Boolean val) {
            front = val;
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
