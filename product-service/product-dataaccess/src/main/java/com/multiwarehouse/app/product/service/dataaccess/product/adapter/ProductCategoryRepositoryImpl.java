package com.multiwarehouse.app.product.service.dataaccess.product.adapter;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.dataaccess.product.mapper.ProductCategoryDataAccessMapper;
import com.multiwarehouse.app.product.service.dataaccess.product.mapper.ProductDataAccessMapper;
import com.multiwarehouse.app.product.service.dataaccess.product.repository.ProductCategoryJpaRepository;
import com.multiwarehouse.app.product.service.dataaccess.product.repository.ProductJpaRepository;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.ports.output.repository.ProductCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDataAccessMapper.productCategoryEntityToProductCategory(productCategoryJpaRepository
                .save(productCategoryDataAccessMapper.productCategoryToProductCategoryEntity(productCategory)));
    }

    public void delete(ProductCategory productCategory) {
        productCategoryJpaRepository.delete(productCategoryDataAccessMapper.productCategoryToProductCategoryEntity(productCategory));
    }

    @Override
    public Optional<ProductCategory> findById(ProductCategoryId productCategoryId) {
        return productCategoryJpaRepository.findById(productCategoryId.getValue()).map(productCategoryDataAccessMapper::productCategoryEntityToProductCategory);
    }
}
