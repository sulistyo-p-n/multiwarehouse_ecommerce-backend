package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoriesCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.mapper.ProductCategoryDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ProductCategoriesGetCommandHandler {

    private final ProductCategoryDataMapper productCategoryDataMapper;
    private final ProductCategoryHelper productCategoryHelper;

    public ProductCategoriesGetCommandHandler(ProductCategoryDataMapper productCategoryDataMapper, ProductCategoryHelper productCategoryHelper) {
        this.productCategoryDataMapper = productCategoryDataMapper;
        this.productCategoryHelper = productCategoryHelper;
    }

    @Transactional(readOnly = true)
    public List<GetProductCategoryResponse> getProductCategories(GetProductCategoriesCommand getProductCategoriesCommand) {
        List<ProductCategory> productCategories = productCategoryHelper.findProductCategories(getProductCategoriesCommand);
        log.info("Product category is selected with total: {}", productCategories.size());
        return productCategories.stream().map(productCategoryDataMapper::getProductCategoryResponseFromProductCategory).collect(Collectors.toList());
    }
}
