package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;

public class ProductCategoryDomainServiceImpl implements ProductCategoryDomainService {
    @Override
    public ProductCategory validateAndInitializeProductCategory(ProductCategory productCategory) {
        productCategory.validateInitialization();
        productCategory.initialize();
        return productCategory;
    }

    @Override
    public ProductCategory validateAndSetProductCategory(ProductCategory productCategory, ProductCategory newProductCategory) {
        productCategory.validateId();
        productCategory.setCode(newProductCategory.getCode());
        productCategory.setName(newProductCategory.getName());
        productCategory.setDescription(newProductCategory.getDescription());
        productCategory.setActive(newProductCategory.isActive());
        return productCategory;
    }
}
