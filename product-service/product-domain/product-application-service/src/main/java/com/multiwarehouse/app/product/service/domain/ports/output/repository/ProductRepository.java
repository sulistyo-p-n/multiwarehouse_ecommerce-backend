package com.multiwarehouse.app.product.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findByCriteria(Boolean withInactive, Boolean withTrashed, ProductCategoryId productCategoryId, String search);
    Optional<Product> findById(ProductId productId);
    Product save(Product product);
    void hardDelete(Product product);
    void softDelete(Product product);
}
