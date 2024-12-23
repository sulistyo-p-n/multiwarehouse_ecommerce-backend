package com.multiwarehouse.app.product.service.dataaccess.product.mapper;

import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductEntity;
import com.multiwarehouse.app.product.service.domain.entity.Product;

public class ProductDataAccessMapper {
    public Product productEntityToProduct(ProductEntity productEntity) {
        return Product.builder()
                .withId(new ProductId(productEntity.getId()))
                .withCode(productEntity.getCode())
                .withName(productEntity.getName())
                .withDescription(productEntity.getDescription())
                .withPrice(new Money(productEntity.getPrice()))
                .build();
    }

    public ProductEntity productToProductEntity(Product product) {
        return ProductEntity.builder()
                .id(product.getId().getValue())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .build();
    }
}
