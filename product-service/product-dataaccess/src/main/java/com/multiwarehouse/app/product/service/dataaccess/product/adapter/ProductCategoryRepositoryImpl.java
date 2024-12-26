package com.multiwarehouse.app.product.service.dataaccess.product.adapter;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductCategoryEntity;
import com.multiwarehouse.app.product.service.dataaccess.product.mapper.ProductCategoryDataAccessMapper;
import com.multiwarehouse.app.product.service.dataaccess.product.repository.ProductCategoryJpaRepository;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.ports.output.repository.ProductCategoryRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {

    private final ProductCategoryJpaRepository productCategoryJpaRepository;
    private final ProductCategoryDataAccessMapper productCategoryDataAccessMapper;

    public ProductCategoryRepositoryImpl(ProductCategoryJpaRepository productCategoryJpaRepository,
                                 ProductCategoryDataAccessMapper productCategoryDataAccessMapper) {
        this.productCategoryJpaRepository = productCategoryJpaRepository;
        this.productCategoryDataAccessMapper = productCategoryDataAccessMapper;
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryJpaRepository.findAllNotDeleted().stream().map(productCategoryDataAccessMapper::productCategoryEntityToProductCategory).collect(Collectors.toList());
    }

    @Override
    public List<ProductCategory> findByCriteria(Boolean withInactive, Boolean withTrashed, String search) {
        return productCategoryJpaRepository.findByCriteria(withInactive, withTrashed, search).stream().map(productCategoryDataAccessMapper::productCategoryEntityToProductCategory).collect(Collectors.toList());
    }

    @Override
    public Optional<ProductCategory> findById(ProductCategoryId productCategoryId) {
        return productCategoryJpaRepository.findById(productCategoryId.getValue()).map(productCategoryDataAccessMapper::productCategoryEntityToProductCategory);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDataAccessMapper.productCategoryEntityToProductCategory(productCategoryJpaRepository
                .save(productCategoryDataAccessMapper.productCategoryToProductCategoryEntity(productCategory)));
    }

    @Override
    public void hardDelete(ProductCategory productCategory) {
        productCategoryJpaRepository.delete(productCategoryDataAccessMapper.productCategoryToProductCategoryEntity(productCategory));
    }

    @Override
    public void softDelete(ProductCategory productCategory) {
        ProductCategoryEntity productCategoryEntity = productCategoryDataAccessMapper.productCategoryToProductCategoryEntity(productCategory);
        productCategoryEntity.setDeletedAt(Instant.now());
        productCategoryJpaRepository.save(productCategoryEntity);
    }
}
