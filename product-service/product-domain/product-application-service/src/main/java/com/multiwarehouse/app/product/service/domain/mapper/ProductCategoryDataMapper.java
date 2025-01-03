package com.multiwarehouse.app.product.service.domain.mapper;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryDataMapper {

    public ProductCategory productCategoryFromCreateProductCategoryCommand(CreateProductCategoryCommand createProductCategoryCommand) {
        return ProductCategory.builder()
                .withCode(createProductCategoryCommand.getCode())
                .withName(createProductCategoryCommand.getName())
                .withDescription(createProductCategoryCommand.getDescription())
                .withActive(createProductCategoryCommand.getActive())
                .build();
    }

    public CreateProductCategoryResponse createProductCategoryResponseFromProductCategory(ProductCategory productCategory) {
        return CreateProductCategoryResponse.builder()
                .id(productCategory.getId().getValue())
                .build();
    }

    public ProductCategory productCategoryFromUpdateProductCategoryCommand(UpdateProductCategoryCommand updateProductCategoryCommand) {
        return ProductCategory.builder()
                .withId(new ProductCategoryId(updateProductCategoryCommand.getId()))
                .withCode(updateProductCategoryCommand.getCode())
                .withName(updateProductCategoryCommand.getName())
                .withDescription(updateProductCategoryCommand.getDescription())
                .withActive(updateProductCategoryCommand.getActive())
                .build();
    }

    public UpdateProductCategoryResponse updateProductCategoryResponseFromProductCategory(ProductCategory productCategory) {
        return UpdateProductCategoryResponse.builder()
                .id(productCategory.getId().getValue())
                .build();
    }

    public GetProductCategoryResponse getProductCategoryResponseFromProductCategory(ProductCategory productCategory) {
        return GetProductCategoryResponse.builder()
                .id(productCategory.getId().getValue())
                .code(productCategory.getCode())
                .name(productCategory.getName())
                .description(productCategory.getDescription())
                .active(productCategory.isActive())
                .isSoftDeleted(productCategory.isSoftDeleted())
                .build();
    }

}
