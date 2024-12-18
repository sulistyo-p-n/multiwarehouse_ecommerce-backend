package com.multiwarehouse.app.warehouse.service.dataaccess.product.adapter;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.warehouse.service.dataaccess.product.mapper.ProductDataAccessMapper;
import com.multiwarehouse.app.warehouse.service.dataaccess.product.repository.ProductJpaRepository;
import com.multiwarehouse.app.warehouse.service.domain.entity.Product;
import com.multiwarehouse.app.warehouse.service.domain.port.output.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductDataAccessMapper productDataAccessMapper;

    public ProductRepositoryImpl(ProductJpaRepository productJpaRepository,
                                 ProductDataAccessMapper productDataAccessMapper) {
        this.productJpaRepository = productJpaRepository;
        this.productDataAccessMapper = productDataAccessMapper;
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return productJpaRepository.findById(productId.getValue()).map(productDataAccessMapper::productEntityToProduct);
    }
}
