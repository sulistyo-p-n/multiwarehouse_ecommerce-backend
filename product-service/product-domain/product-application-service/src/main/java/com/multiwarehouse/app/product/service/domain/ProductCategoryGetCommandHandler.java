package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.mapper.ProductCategoryDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProductCategoryGetCommandHandler {

    private final ProductCategoryDataMapper productCategoryDataMapper;
    private final ProductCategoryHelper productCategoryHelper;

    public ProductCategoryGetCommandHandler(ProductCategoryDataMapper productCategoryDataMapper, ProductCategoryHelper productCategoryHelper) {
        this.productCategoryDataMapper = productCategoryDataMapper;
        this.productCategoryHelper = productCategoryHelper;
    }

    @Transactional
    public GetProductCategoryResponse getProductCategory(GetProductCategoryCommand getProductCategoryCommand) {
        ProductCategoryId productCategoryId = new ProductCategoryId(getProductCategoryCommand.getId());
        ProductCategory productCategory = productCategoryHelper.findProductCategoryById(productCategoryId);
        log.info("Product category is selected with id: {}", productCategoryId.getValue());
        return productCategoryDataMapper.getCategoryToGetProductCategoryResponse(productCategory);
    }
}
