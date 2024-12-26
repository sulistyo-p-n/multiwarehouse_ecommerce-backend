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
    private final ProductCategory productCategory;
    private final List<ProductImage> productImages;
    private final ProductStock productStock;
    private Boolean active;

    public void initialize() {
        setId(new ProductId(UUID.randomUUID()));
        initializeProductImages();
    }

    private void initializeProductImages() {
        for (ProductImage productImage : productImages) {
            productImage.initialize();
            productImage.setProductId(getId());
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
        validateProductCategory();
        validateActive();
    }

    private void validateCode() {
        if (getCode() == null || getCode().isEmpty()) throw new ProductDomainException("Product Code cannot be empty");
    }

    private void validateName() {
        if (getName() == null || getName().isEmpty()) throw new ProductDomainException("Product Name cannot be empty");
    }

    private void validatePrice() {
        if (getPrice() == null || !getPrice().isGreaterThanZero()) throw new ProductDomainException("Product Price must be greater than zero");
    }

    private void validateProductCategory() {
        if (getProductCategory() == null) throw new ProductDomainException("Product Active cannot be null");
    }

    private void validateActive() {
        if (getActive() == null) throw new ProductDomainException("Product Active cannot be null");
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

    public void setProductCategory(ProductCategory value) {
        if (value == null) return;

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

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public ProductStock getProductStock() {
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
        productCategory = builder.productCategory;
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
        private ProductCategory productCategory;
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
            productCategory = val;
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
