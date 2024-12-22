package com.multiwarehouse.app.product.service.domain.ports.input.service;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductsCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductsResponse;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductResponse;
import jakarta.validation.Valid;

public interface ProductApplicationService {
    public CreateProductResponse createProduct(@Valid CreateProductCommand createProductCommand);

    public UpdateProductResponse updateProduct(@Valid UpdateProductCommand updateProductCommand);

    public DeleteProductResponse deleteProduct(@Valid DeleteProductCommand deleteProductCommand);

    public GetProductsResponse getProducts(@Valid GetProductsCommand getProductsCommand);

    public GetProductResponse getProduct(@Valid GetProductCommand getProductCommand);
}
