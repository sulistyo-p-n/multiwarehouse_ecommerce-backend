package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.mapper.ProductCategoryDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProductCategoryCreateCommandHandler {

    private final ProductCategoryDomainService productCategoryDomainService;
    private final ProductCategoryDataMapper productCategoryDataMapper;
    private final ProductCategoryHelper productCategoryHelper;

    public ProductCategoryCreateCommandHandler(ProductCategoryDomainService productCategoryDomainService, ProductCategoryDataMapper productCategoryDataMapper, ProductCategoryHelper productCategoryHelper) {
        this.productCategoryDomainService = productCategoryDomainService;
        this.productCategoryDataMapper = productCategoryDataMapper;
        this.productCategoryHelper = productCategoryHelper;
    }

    @Transactional
    public CreateProductCategoryResponse createProductCategory(CreateProductCategoryCommand createProductCategoryCommand) {
        ProductCategory productCategory = productCategoryDataMapper.createProductCategoryCommandToProductCategory(createProductCategoryCommand);
        ProductCategory productCategoryValidated = productCategoryDomainService.validateAndInitiateCategory(productCategory);
        ProductCategory productCategorySaved = productCategoryHelper.saveProductCategory(productCategoryValidated);
        log.info("Product Category is created with id: {}", productCategory.getId().getValue());
        return productCategoryDataMapper.productCategoryToCreateProductCategoryResponse(productCategoryValidated);
    }
}
