package com.multiwarehouse.app.product.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductCategoryRepository {
    List<ProductCategory> findAll();
    List<ProductCategory> findByCriteria(Boolean withInactive, Boolean withTrashed, String search);
    Optional<ProductCategory> findById(ProductCategoryId productCategoryId);
    ProductCategory save(ProductCategory productCategory);
    void hardDelete(ProductCategory productCategory);
    void softDelete(ProductCategory productCategory);
}
