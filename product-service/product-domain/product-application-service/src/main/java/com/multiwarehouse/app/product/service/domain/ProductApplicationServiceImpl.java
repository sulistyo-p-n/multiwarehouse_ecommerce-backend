package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductsCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductResponse;
import com.multiwarehouse.app.product.service.domain.ports.input.service.ProductApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
public class ProductApplicationServiceImpl implements ProductApplicationService {

    private final ProductCreateCommandHandler ProductCreateCommandHandler;
    private final ProductDeleteCommandHandler ProductDeleteCommandHandler;
    private final ProductUpdateCommandHandler ProductUpdateCommandHandler;
    private final ProductGetCommandHandler ProductGetCommandHandler;
    private final ProductsGetCommandHandler ProductsGetCommandHandler;

    public ProductApplicationServiceImpl(ProductCreateCommandHandler ProductCreateCommandHandler, ProductDeleteCommandHandler ProductDeleteCommandHandler, ProductUpdateCommandHandler ProductUpdateCommandHandler, ProductGetCommandHandler ProductGetCommandHandler, ProductsGetCommandHandler ProductsGetCommandHandler) {
        this.ProductCreateCommandHandler = ProductCreateCommandHandler;
        this.ProductDeleteCommandHandler = ProductDeleteCommandHandler;
        this.ProductUpdateCommandHandler = ProductUpdateCommandHandler;
        this.ProductGetCommandHandler = ProductGetCommandHandler;
        this.ProductsGetCommandHandler = ProductsGetCommandHandler;
    }

    @Override
    public CreateProductResponse createProduct(CreateProductCommand createProductCommand) {
        return ProductCreateCommandHandler.createProduct(createProductCommand);
    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductCommand updateProductCommand) {
        return ProductUpdateCommandHandler.updateProduct(updateProductCommand);
    }

    @Override
    public DeleteProductResponse deleteProduct(DeleteProductCommand deleteProductCommand) {
        return ProductDeleteCommandHandler.deleteProduct(deleteProductCommand);
    }

    @Override
    public List<GetProductResponse> getProducts(GetProductsCommand getProductsCommand) {
        return ProductsGetCommandHandler.getProducts(getProductsCommand);
    }

    @Override
    public GetProductResponse getProduct(GetProductCommand getProductCommand) {
        return ProductGetCommandHandler.getProduct(getProductCommand);
    }
}
