package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;

public interface ProductCategoryDomainService {
    void validateAndInitiateCategory(ProductCategory category);
}
