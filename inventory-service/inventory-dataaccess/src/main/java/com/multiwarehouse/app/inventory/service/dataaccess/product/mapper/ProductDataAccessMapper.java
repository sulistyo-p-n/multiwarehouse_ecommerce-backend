package com.multiwarehouse.app.inventory.service.dataaccess.product.mapper;

import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.inventory.service.dataaccess.product.entity.ProductEntity;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDataAccessMapper {
    public Product productFromProductEntity(ProductEntity productEntity) {
        return Product.builder()
                .withId(new ProductId(productEntity.getId()))
                .withProductCategoryId(new ProductCategoryId(productEntity.getCategoryId()))
                .withCode(productEntity.getCode())
                .withName(productEntity.getName())
                .withPrice(new Money(productEntity.getPrice()))
                .withActive(productEntity.getActive())
                .withSoftDeleted(productEntity.isSoftDeleted())
                .build();
    }

    public ProductEntity productEntityFromProduct(Product product) {
        return ProductEntity.builder()
                .id(product.getId().getValue())
                .categoryId(product.getProductCategoryId().getValue())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice().getAmount())
                .active(product.isActive())
                .build();
    }
}
