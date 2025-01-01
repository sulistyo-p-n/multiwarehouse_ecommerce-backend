package com.multiwarehouse.app.inventory.service.domain.ports.ouput.repository;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;

import java.time.Instant;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(ProductId productId);
    Product save(Product product);
    void hardDelete(Product product);
    void softDelete(Product product);
}
