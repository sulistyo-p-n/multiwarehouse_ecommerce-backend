package com.multiwarehouse.app.product.service.domain.ports.input.service;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoriesCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface ProductCategoryApplicationService {
    public CreateProductCategoryResponse createProductCategory(@Valid CreateProductCategoryCommand createProductCategoryCommand);

    public UpdateProductCategoryResponse updateProductCategory(@Valid UpdateProductCategoryCommand updateProductCategoryCommand);

    public DeleteProductCategoryResponse deleteProductCategory(@Valid DeleteProductCategoryCommand deleteProductCategoryCommand);

    public List<GetProductCategoryResponse> getProductCategories(@Valid GetProductCategoriesCommand getProductCategoriesCommand);

    public GetProductCategoryResponse getProductCategory(@Valid GetProductCategoryCommand getProductCategoryCommand);
}

