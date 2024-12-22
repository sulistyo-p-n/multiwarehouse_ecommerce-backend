package com.multiwarehouse.app.product.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(ProductId productId);
    List<Product> findByProductCategoryId(ProductCategoryId productCategoryId);

    Product save(Product product);
}
