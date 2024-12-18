package com.multiwarehouse.app.warehouse.service.domain.port.output.repository;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.warehouse.service.domain.entity.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> findById(ProductId productId);
}
