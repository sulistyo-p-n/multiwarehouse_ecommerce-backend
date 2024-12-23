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
import com.multiwarehouse.app.product.service.domain.ports.input.service.ProductCategoryApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class ProductCategoryApplicationServiceImpl implements ProductCategoryApplicationService {

    private final ProductCategoryCreateCommandHandler productCategoryCreateCommandHandler;

    public ProductCategoryApplicationServiceImpl(ProductCategoryCreateCommandHandler productCategoryCreateCommandHandler) {
        this.productCategoryCreateCommandHandler = productCategoryCreateCommandHandler;
    }

    @Override
    public CreateProductCategoryResponse createProductCategory(CreateProductCategoryCommand createProductCategoryCommand) {
        return productCategoryCreateCommandHandler.createProductCategory(createProductCategoryCommand);
    }

    @Override
    public UpdateProductCategoryResponse updateProductCategory(UpdateProductCategoryCommand updateProductCategoryCommand) {
        return productCategoryCreateCommandHandler.updateProductCategory(updateProductCategoryCommand);
    }

    @Override
    public DeleteProductCategoryResponse deleteProductCategory(DeleteProductCategoryCommand deleteProductCategoryCommand) {
        return productCategoryCreateCommandHandler.deleteProductCategory(deleteProductCategoryCommand);
    }

    @Override
    public GetProductCategoriesResponse getProductCategories(GetProductCategoriesCommand getProductCategoriesCommand) {
        return productCategoryCreateCommandHandler.getProductCategories(getProductCategoriesCommand);
    }

    @Override
    public GetProductCategoryResponse getProductCategory(GetProductCategoryCommand getProductCategoryCommand) {
        return productCategoryCreateCommandHandler.getProductCategory(getProductCategoryCommand);
    }
}
