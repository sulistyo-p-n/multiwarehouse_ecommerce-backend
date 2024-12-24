package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoriesCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoriesResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.mapper.ProductCategoryDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class ProductCategoriesGetCommandHandler {

    private final ProductCategoryDataMapper productCategoryDataMapper;
    private final ProductCategoryHelper productCategoryHelper;

    public ProductCategoriesGetCommandHandler(ProductCategoryDataMapper productCategoryDataMapper, ProductCategoryHelper productCategoryHelper) {
        this.productCategoryDataMapper = productCategoryDataMapper;
        this.productCategoryHelper = productCategoryHelper;
    }

    @Transactional
    public GetProductCategoriesResponse getProductCategories(GetProductCategoriesCommand getProductCategoriesCommand) {
        List<ProductCategory> productCategories = productCategoryHelper.findProductCategories();
        log.info("Product category is selected with total: {}", productCategories.size());
        return productCategoryDataMapper.getCategoriesToGetProductCategoriesResponse(productCategories);
    }
}
