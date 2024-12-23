package com.multiwarehouse.app.product.service.domain.mapper;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryDataMapper {

    public ProductCategory createProductCategoryCommandToProductCategory(CreateProductCategoryCommand createProductCategoryCommand) {
        return ProductCategory.builder()
                .withCode(createProductCategoryCommand.getCode())
                .withName(createProductCategoryCommand.getName())
                .withDescription(createProductCategoryCommand.getDescription())
                .withActive(createProductCategoryCommand.getActive())
                .build();
    }

    public CreateProductCategoryResponse productCategoryToCreateProductCategoryResponse(ProductCategory productCategory) {
        return CreateProductCategoryResponse.builder()
                .id(productCategory.getId().getValue())
                .build();
    }
}
