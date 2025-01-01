package com.multiwarehouse.app.inventory.service.dataaccess.product.adapter;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.inventory.service.dataaccess.product.entity.ProductEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.product.mapper.ProductDataAccessMapper;
import com.multiwarehouse.app.inventory.service.dataaccess.product.repository.ProductJpaRepository;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.ports.ouput.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
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
        return productJpaRepository.findById(productId.getValue()).map(productDataAccessMapper::productFromProductEntity);
    }

    @Override
    public Product save(Product product) {
        return productDataAccessMapper.productFromProductEntity(productJpaRepository
                .save(productDataAccessMapper.productEntityFromProduct(product)));
    }

    @Override
    public void hardDelete(Product product) {
        productJpaRepository.delete(productDataAccessMapper.productEntityFromProduct(product));
    }

    @Override
    public void softDelete(Product product) {
        ProductEntity productEntity = productDataAccessMapper.productEntityFromProduct(product);
        productEntity.setDeletedAt(Instant.now());
        productJpaRepository.save(productEntity);
    }
}
