package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.exception.ProductCategoryDomainException;
import com.multiwarehouse.app.product.service.domain.mapper.ProductCategoryDataMapper;
import com.multiwarehouse.app.product.service.domain.ports.output.repository.ProductCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProductCategoryCreateHelper {
    private final ProductCategoryDomainService productCategoryDomainService;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryDataMapper productCategoryDataMapper;

    public ProductCategoryCreateHelper(ProductCategoryDomainService productCategoryDomainService, ProductCategoryRepository productCategoryRepository, ProductCategoryDataMapper productCategoryDataMapper) {
        this.productCategoryDomainService = productCategoryDomainService;
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryDataMapper = productCategoryDataMapper;
    }

    @Transactional
    public ProductCategory persistProductCategory(CreateProductCategoryCommand createProductCategoryCommand) {
        ProductCategory productCategory = productCategoryDataMapper.createProductCategoryCommandToProductCategory(createProductCategoryCommand);
        productCategoryDomainService.validateAndInitiateCategory(productCategory);
        return saveProductCategory(productCategory);
    }

    private ProductCategory saveProductCategory(ProductCategory productCategory) {
        ProductCategory productCategoryResult = productCategoryRepository.save(productCategory);
        if (productCategoryResult == null) {
            log.error("Could not save product category!");
            throw new ProductCategoryDomainException("Cloud not save product category!");
        }
        log.info("Product category is saved with id : {}", productCategoryResult.getId().getValue());
        return productCategoryResult;
    }
}
