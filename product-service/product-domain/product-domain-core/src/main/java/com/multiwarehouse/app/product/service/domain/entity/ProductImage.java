package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.exception.ProductDomainException;
import com.multiwarehouse.app.product.service.domain.valueobject.ProductImageId;

import java.util.UUID;

public class ProductImage extends BaseEntity<ProductImageId> {
    private final ProductId productId;
    private final String code;
    private final String name;
    private final String description;
    private final String path;
    private final Boolean active;

    public void initializeProductImage() {
        setId(new ProductImageId(UUID.randomUUID()));
    }

    public void validationInitialProductImage() {
        if (getId() != null) {
            throw  new ProductDomainException("ProductImage is not in correct state for initialization");
        }
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
