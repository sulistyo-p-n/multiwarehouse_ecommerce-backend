package com.multiwarehouse.app.product.service.dataaccess.product.adapter;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.dataaccess.product.mapper.ProductDataAccessMapper;
import com.multiwarehouse.app.product.service.dataaccess.product.repository.ProductJpaRepository;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.ports.output.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Product save(Product product) {
        return productDataAccessMapper.productEntityToProduct(productJpaRepository
                .save(productDataAccessMapper.productToProductEntity(product)));
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return productJpaRepository.findById(productId.getValue()).map(productDataAccessMapper::productEntityToProduct);
    }

    @Override
    public List<Product> findByProductCategoryId(ProductCategoryId productCategoryId) {
        return productJpaRepository.findAll().stream().map(productDataAccessMapper::productEntityToProduct).collect(Collectors.toList());
    }
}
