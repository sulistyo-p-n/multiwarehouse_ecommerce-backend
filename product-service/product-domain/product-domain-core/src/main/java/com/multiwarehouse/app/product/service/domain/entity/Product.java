package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.exception.ProductDomainException;

import java.util.List;
import java.util.UUID;

public class Product extends AggregateRoot<ProductId> {
    private final String code;
    private final String name;
    private final String description;
    private final Money price;
    private final ProductCategory category;
    private final List<ProductImage> productImages;
    private final ProductStock productStock;
    private final Boolean active;

    public void initializeProduct() {
        setId(new ProductId(UUID.randomUUID()));
    }

    public void validationInitialProduct() {
        if (getId() != null) {
            throw  new ProductDomainException("Warehouse is not in correct state for initialization");
        }
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

    public Money getPrice() {
        return price;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public ProductStock getStock() {
        return productStock;
    }

    public Boolean getActive() {
        return active;
    }

    private Product(Builder builder) {
        super.setId(builder.id);
        code = builder.code;
        name = builder.name;
        description = builder.description;
        price = builder.price;
        category = builder.category;
        productImages = builder.productImages;
        productStock = builder.productStock;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ProductId id;
        private String code;
        private String name;
        private String description;
        private Money price;
        private ProductCategory category;
        private List<ProductImage> productImages;
        private ProductStock productStock;
        private Boolean active;

        private Builder() {
        }

        public Builder withId(ProductId val) {
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

        public Builder withPrice(Money val) {
            price = val;
            return this;
        }

        public Builder withCategory(ProductCategory val) {
            category = val;
            return this;
        }

        public Builder withProductImages(List<ProductImage> val) {
            productImages = val;
            return this;
        }

        public Builder withStock(ProductStock val) {
            productStock = val;
            return this;
        }

        public Builder withActive(Boolean val) {
            active = val;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}
