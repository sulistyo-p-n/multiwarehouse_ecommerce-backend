package com.multiwarehouse.app.product.service.dataaccess.product.adapter;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductEntity;
import com.multiwarehouse.app.product.service.dataaccess.product.mapper.ProductDataAccessMapper;
import com.multiwarehouse.app.product.service.dataaccess.product.repository.ProductJpaRepository;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.ports.output.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
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
    public List<Product> findAll() {
        return productJpaRepository.findAllNotDeleted().stream().map(productDataAccessMapper::productEntityToProduct).collect(Collectors.toList());
    }

    @Override
    public List<Product> findByCriteria(Boolean withInactive, Boolean withTrashed, ProductCategoryId productCategoryId) {
        return productJpaRepository.findByCriteria(withInactive, withTrashed, productCategoryId.getValue()).stream().map(productDataAccessMapper::productEntityToProduct).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(ProductId productId) {
        return productJpaRepository.findNotDeletedById(productId.getValue()).map(productDataAccessMapper::productEntityToProduct);
    }

    @Override
    public Product save(Product product) {
        return productDataAccessMapper.productEntityToProduct(productJpaRepository
                .save(productDataAccessMapper.productToProductEntity(product)));
    }

    @Override
    public void hardDelete(Product product) {
        productJpaRepository.delete(productDataAccessMapper.productToProductEntity(product));
    }

    @Override
    public void softDelete(Product product) {
        ProductEntity productEntity = productDataAccessMapper.productToProductEntity(product);
        productEntity.setDeletedAt(Instant.now());
        productJpaRepository.save(productEntity);
    }
}
