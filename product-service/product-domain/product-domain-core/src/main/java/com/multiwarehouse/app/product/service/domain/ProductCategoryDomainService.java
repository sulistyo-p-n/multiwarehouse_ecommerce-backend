package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;

public interface ProductCategoryDomainService {
    ProductCategory validateAndInitiateCategory(ProductCategory productCategory);
    ProductCategory validateAndUpdateCategory(ProductCategory productCategory, ProductCategory newProductCategory);
}
