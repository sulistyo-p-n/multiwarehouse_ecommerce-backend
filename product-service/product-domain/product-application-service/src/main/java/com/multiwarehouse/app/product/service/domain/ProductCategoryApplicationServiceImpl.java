package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoriesCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.ports.input.service.ProductCategoryApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
public class ProductCategoryApplicationServiceImpl implements ProductCategoryApplicationService {

    private final ProductCategoryCreateCommandHandler productCategoryCreateCommandHandler;
    private final ProductCategoryDeleteCommandHandler productCategoryDeleteCommandHandler;
    private final ProductCategoryUpdateCommandHandler productCategoryUpdateCommandHandler;
    private final ProductCategoryGetCommandHandler productCategoryGetCommandHandler;
    private final ProductCategoriesGetCommandHandler productCategoriesGetCommandHandler;

    public ProductCategoryApplicationServiceImpl(ProductCategoryCreateCommandHandler productCategoryCreateCommandHandler, ProductCategoryDeleteCommandHandler productCategoryDeleteCommandHandler, ProductCategoryUpdateCommandHandler productCategoryUpdateCommandHandler, ProductCategoryGetCommandHandler productCategoryGetCommandHandler, ProductCategoriesGetCommandHandler productCategoriesGetCommandHandler) {
        this.productCategoryCreateCommandHandler = productCategoryCreateCommandHandler;
        this.productCategoryDeleteCommandHandler = productCategoryDeleteCommandHandler;
        this.productCategoryUpdateCommandHandler = productCategoryUpdateCommandHandler;
        this.productCategoryGetCommandHandler = productCategoryGetCommandHandler;
        this.productCategoriesGetCommandHandler = productCategoriesGetCommandHandler;
    }

    @Override
    public CreateProductCategoryResponse createProductCategory(CreateProductCategoryCommand createProductCategoryCommand) {
        return productCategoryCreateCommandHandler.createProductCategory(createProductCategoryCommand);
    }

    @Override
    public UpdateProductCategoryResponse updateProductCategory(UpdateProductCategoryCommand updateProductCategoryCommand) {
        return productCategoryUpdateCommandHandler.updateProductCategory(updateProductCategoryCommand);
    }

    @Override
    public DeleteProductCategoryResponse deleteProductCategory(DeleteProductCategoryCommand deleteProductCategoryCommand) {
        return productCategoryDeleteCommandHandler.deleteProductCategory(deleteProductCategoryCommand);
    }

    @Override
    public List<GetProductCategoryResponse> getProductCategories(GetProductCategoriesCommand getProductCategoriesCommand) {
        return productCategoriesGetCommandHandler.getProductCategories(getProductCategoriesCommand);
    }

    @Override
    public GetProductCategoryResponse getProductCategory(GetProductCategoryCommand getProductCategoryCommand) {
        return productCategoryGetCommandHandler.getProductCategory(getProductCategoryCommand);
    }
}
