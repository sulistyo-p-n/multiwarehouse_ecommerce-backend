package com.multiwarehouse.app.product.service.domain.rest;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/products", produces = "application/vnd.api.v1+json")
public class ProductController {
    private final ProductApplicationService productApplicationService;

    public ProductController(ProductApplicationService productApplicationService) {
        this.productApplicationService = productApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<GetProductResponse>> getProducts(GetProductsCommand getProductsCommand) {
        log.info("Getting products: {}", getProductsCommand);
        List<GetProductResponse> getProductsResponse = productApplicationService.getProducts(getProductsCommand);
        log.info("Returning products size: {}", getProductsResponse.size());
        return ResponseEntity.ok(getProductsResponse);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<GetProductResponse> getProduct(@PathVariable("id") UUID id) {
        log.info("Getting product by id: {}", id);
        GetProductCommand getProductCommand = GetProductCommand.builder().id(id).build();
        GetProductResponse getProductResponse = productApplicationService.getProduct(getProductCommand);
        log.info("Returning product: {}", getProductResponse);
        return ResponseEntity.ok(getProductResponse);
    }

    @PostMapping
    public ResponseEntity<CreateProductResponse> createProduct(@RequestBody CreateProductCommand createProductCommand) {
        log.info("Creating product: {}", createProductCommand);
        CreateProductResponse createProductResponse = productApplicationService.createProduct(createProductCommand);
        log.info("Product created: {}", createProductResponse);
        return ResponseEntity.ok(createProductResponse);

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UpdateProductResponse> updateProduct(@PathVariable("id") UUID id, @RequestBody UpdateProductCommand updateProductCommand) {
        log.info("Updating product with id: {}", id);
        updateProductCommand.setId(id);
        UpdateProductResponse updateProductResponse = productApplicationService.updateProduct(updateProductCommand);
        log.info("Product updated with: {}", updateProductResponse);
        return ResponseEntity.ok(updateProductResponse);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteProductResponse> deleteProduct(@PathVariable("id") UUID id, @RequestBody Optional<DeleteProductCommand> deleteProductCommand) {
        log.info("Deleting product with id: {}", id);
        DeleteProductCommand deleteProductCommandWithId = deleteProductCommand.orElse(DeleteProductCommand.builder().build());
        deleteProductCommandWithId.setId(id);
        DeleteProductResponse deleteProductResponse = productApplicationService.deleteProduct(deleteProductCommandWithId);
        log.info("Product deleted with id: {}", id);
        return ResponseEntity.ok(deleteProductResponse);
    }
}
