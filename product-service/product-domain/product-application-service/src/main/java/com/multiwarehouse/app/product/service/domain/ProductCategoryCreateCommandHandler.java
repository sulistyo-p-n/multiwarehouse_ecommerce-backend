package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoriesCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoriesResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.mapper.ProductCategoryDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductCategoryCreateCommandHandler {

    private final ProductCategoryCreateHelper productCategoryCreateHelper;
    private final ProductCategoryDataMapper productCategoryDataMapper;

    public ProductCategoryCreateCommandHandler(ProductCategoryCreateHelper productCategoryCreateHelper, ProductCategoryDataMapper productCategoryDataMapper) {
        this.productCategoryCreateHelper = productCategoryCreateHelper;
        this.productCategoryDataMapper = productCategoryDataMapper;
    }

    public CreateProductCategoryResponse createProductCategory(CreateProductCategoryCommand createProductCategoryCommand) {
        ProductCategory productCategory = productCategoryCreateHelper.persistProductCategory(createProductCategoryCommand);
        log.info("Product category is created with id: {}", productCategory.getId().getValue());
        return productCategoryDataMapper.productCategoryToCreateProductCategoryResponse(productCategory);
    }

//    public UpdateProductCategoryResponse updateProductCategory(UpdateProductCategoryCommand updateProductCategoryCommand) {
//        return null;
//    }
//
//    public DeleteProductCategoryResponse deleteProductCategory(DeleteProductCategoryCommand deleteProductCategoryCommand) {
//        return null;
//    }
//
//    public GetProductCategoriesResponse getProductCategories(GetProductCategoriesCommand getProductCategoriesCommand) {
//        return null;
//    }
//
//    public GetProductCategoryResponse getProductCategory(GetProductCategoryCommand getProductCategoryCommand) {
//        return null;
//    }
}
