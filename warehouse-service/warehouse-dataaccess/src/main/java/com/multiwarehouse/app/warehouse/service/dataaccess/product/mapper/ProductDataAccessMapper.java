package com.multiwarehouse.app.warehouse.service.dataaccess.product.mapper;

import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.warehouse.service.dataaccess.product.entity.ProductCategoryEntity;
import com.multiwarehouse.app.warehouse.service.dataaccess.product.entity.ProductEntity;
import com.multiwarehouse.app.warehouse.service.domain.entity.Product;
import com.multiwarehouse.app.warehouse.service.domain.entity.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductDataAccessMapper {
    public Product productEntityToProduct(ProductEntity productEntity) {
        return Product.builder()
                .withId(new ProductId(productEntity.getId()))
                .withName(productEntity.getName())
                .withDesc(productEntity.getDescription())
                .withPrice(new Money(productEntity.getPrice()))
                .withProductCategory(productCategoryEntityToProductCategory(productEntity.getCategory()))
                .build();
    }

    // TODO it will create new product category, even it is already exist
    private ProductCategory productCategoryEntityToProductCategory(ProductCategoryEntity productCategoryEntity) {
        return ProductCategory.builder()
                .withId(new ProductCategoryId(productCategoryEntity.getId()))
                .withName(productCategoryEntity.getName())
                .withDesc(productCategoryEntity.getDescription())
                .build();
    }
}
