package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.mapper.ProductCategoryDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class ProductCategoryUpdateCommandHandler {
    private final ProductCategoryDomainService productCategoryDomainService;
    private final ProductCategoryDataMapper productCategoryDataMapper;
    private final ProductCategoryHelper productCategoryHelper;

    public ProductCategoryUpdateCommandHandler(ProductCategoryDomainService productCategoryDomainService, ProductCategoryDataMapper productCategoryDataMapper, ProductCategoryHelper productCategoryHelper) {
        this.productCategoryDomainService = productCategoryDomainService;
        this.productCategoryDataMapper = productCategoryDataMapper;
        this.productCategoryHelper = productCategoryHelper;
    }

    @Transactional
    public UpdateProductCategoryResponse updateProductCategory(UpdateProductCategoryCommand updateProductCategoryCommand) {
        ProductCategoryId productCategoryId = new ProductCategoryId(updateProductCategoryCommand.getId());
        ProductCategory productCategory = productCategoryHelper.findProductCategoryById(productCategoryId);
        ProductCategory newProductCategory = productCategoryDataMapper.updateProductCategoryCommandToProductCategory(updateProductCategoryCommand);
        ProductCategory productCategoryChanged = productCategoryDomainService.validateAndUpdateCategory(productCategory, newProductCategory);
        ProductCategory productCategorySaved = productCategoryHelper.saveProductCategory(productCategoryChanged);
        log.info("Product category is updated with id: {}", productCategoryId.getValue());
        return productCategoryDataMapper.updateCategoryToUpdateProductCategoryResponse(productCategorySaved);
    }
}
