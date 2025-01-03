package com.multiwarehouse.app.product.service.dataaccess.product.mapper;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductCategoryEntity;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryDataAccessMapper {
    public ProductCategory productCategoryEntityToProductCategory(ProductCategoryEntity productCategoryEntity) {
        return ProductCategory.builder()
                .withId(new ProductCategoryId(productCategoryEntity.getId()))
                .withCode(productCategoryEntity.getCode())
                .withName(productCategoryEntity.getName())
                .withDescription(productCategoryEntity.getDescription())
                .withActive(productCategoryEntity.getActive())
                .withSoftDeleted(productCategoryEntity.isSoftDeleted())
                .build();
    }

    public ProductCategoryEntity productCategoryToProductCategoryEntity(ProductCategory productCategory) {
        return ProductCategoryEntity.builder()
                .id(productCategory.getId().getValue())
                .code(productCategory.getCode())
                .name(productCategory.getName())
                .description(productCategory.getDescription())
                .active(productCategory.isActive())
                .build();
    }
}
