package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;

public interface ProductCategoryDomainService {
    ProductCategory validateAndInitializeProductCategory(ProductCategory productCategory);
    ProductCategory validateAndSetProductCategory(ProductCategory productCategory, ProductCategory newProductCategory);
}
