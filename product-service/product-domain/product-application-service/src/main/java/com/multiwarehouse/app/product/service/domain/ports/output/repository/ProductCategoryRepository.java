package com.multiwarehouse.app.product.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;

import java.util.Optional;

public interface ProductCategoryRepository {
    Optional<ProductCategory> findById(ProductCategoryId productCategoryId);

    ProductCategory save(ProductCategory productCategory);
}
