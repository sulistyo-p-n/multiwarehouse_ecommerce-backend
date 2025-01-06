package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.exception.ProductDomainException;

import java.util.List;
import java.util.UUID;

public class Product extends AggregateRoot<ProductId> {
    private String code;
    private String name;
    private String description;
    private Money price;
    private ProductCategory category;
    private List<ProductImage> images;
    private Boolean active;

    private final int quantity;

    private final Boolean softDeleted;

    private Product(Builder builder) {
        super.setId(builder.id);
        code = builder.code;
        name = builder.name;
        description = builder.description;
        price = builder.price;
        category = builder.category;
        images = builder.images;
        active = builder.active;
        quantity = builder.quantity;
        softDeleted = builder.softDeleted;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initialize() {
        setId(new ProductId(UUID.randomUUID()));
        initializeProductImages();
    }

    private void initializeProductImages() {
        for (ProductImage image : images) {
            image.initialize();
            image.setProductId(getId());
        }
    }

    public void validateInitialization() {
        validateInitialId();
        validateFields();
    }

    public void validateInitialId() {
        if (getId() != null) {
            throw new ProductDomainException("Product is not in correct state for initialization");
        }
    }

    public void validate() {
        validateId();
        validateFields();
    }

    public void validateId() {
        if (getId() == null ) throw new ProductDomainException("Product Id cannot be empty");
    }

    public void validateFields() {
        validateCode();
        validateName();
        validatePrice();
        validateCategory();
        validateActive();
    }

    private void validateCode() {
        if (code == null || getCode().isEmpty()) throw new ProductDomainException("Product Code cannot be empty");
    }

    private void validateName() {
        if (name == null || getName().isEmpty()) throw new ProductDomainException("Product Name cannot be empty");
    }

    private void validatePrice() {
        if (price == null || !getPrice().isGreaterThanZero()) throw new ProductDomainException("Product Price must be greater than zero");
    }

    private void validateCategory() {
        if (category == null) throw new ProductDomainException("Product Active cannot be null");
    }

    private void validateActive() {
        if (active == null) throw new ProductDomainException("Product Active cannot be null");
    }

    public void setCode(String value) {
        if (value == null) return;
        if (value.isEmpty()) throw  new ProductDomainException("Product Code cannot be empty");
        code = value;
    }

    public void setName(String value) {
        if (value == null) return;
        if (value.isEmpty()) throw  new ProductDomainException("Product Name cannot be empty");
        name = value;
    }

    public void setDescription(String value) {
        if (value == null) return;
        description = value;
    }

    public void setPrice(Money value) {
        if (value == null) return;
        if (!value.isGreaterThanZero()) throw new ProductDomainException("Product Price must be greater than zero");
        price = value;
    }

    public void setCategory(ProductCategory value) {
        if (value == null) return;
        category = value;
    }

    public void setImages(List<ProductImage> values) {
        if (values == null) return;
        images = values;
        initializeProductImages();
    }

    public void setActive(Boolean value) {
        if (value == null) return;
        active = value;
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

    public List<ProductImage> getImages() {
        return images;
    }

    public int getQuantity() {
        return quantity;
    }

    public Boolean isActive() {
        return active;
    }

    public Boolean isSoftDeleted() {
        return softDeleted;
    }

    public static final class Builder {
        private ProductId id;
        private String code;
        private String name;
        private String description;
        private Money price;
        private ProductCategory category;
        private List<ProductImage> images;
        private Boolean active;
        private int quantity = 0;
        private Boolean softDeleted;

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

        public Builder withImages(List<ProductImage> val) {
            images = val;
            return this;
        }

        public Builder withActive(Boolean val) {
            active = val;
            return this;
        }

        public Builder withQuantity(int val) {
            quantity = val;
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
