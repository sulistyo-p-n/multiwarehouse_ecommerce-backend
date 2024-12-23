package com.multiwarehouse.app.product.service.domain.rest;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/product_categories", produces = "application/vnd.api.v1+json")
public class ProductCategoryController {
    private final ProductCategoryApplicationService productCategoryApplicationService;

    public ProductCategoryController(ProductCategoryApplicationService productCategoryApplicationService) {
        this.productCategoryApplicationService = productCategoryApplicationService;
    }

    @GetMapping
    public ResponseEntity<GetProductCategoriesResponse> getProductCategories(GetProductCategoriesCommand getProductCategoriesCommand) {
        log.info("Getting product categories: {}", getProductCategoriesCommand);
        GetProductCategoriesResponse getProductCategoriesResponse = productCategoryApplicationService.getProductCategories(getProductCategoriesCommand);
        log.info("Returning product categories: {}", getProductCategoriesResponse.getProductCategories());
        return ResponseEntity.ok(getProductCategoriesResponse);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<GetProductCategoryResponse> getProductCategory(@PathVariable("id") UUID id) {
        log.info("Getting product category by id: {}", id);
        GetProductCategoryCommand getProductCategoryCommand = GetProductCategoryCommand.builder().id(id).build();
        GetProductCategoryResponse getProductCategoryResponse = productCategoryApplicationService.getProductCategory(getProductCategoryCommand);
        log.info("Returning product category: {}", getProductCategoryResponse);
        return ResponseEntity.ok(getProductCategoryResponse);
    }

    @PostMapping
    public ResponseEntity<CreateProductCategoryResponse> createProductCategory(@RequestBody CreateProductCategoryCommand createProductCategoryCommand) {
        log.info("Creating product category: {}", createProductCategoryCommand);
        CreateProductCategoryResponse createProductCategoryResponse = productCategoryApplicationService.createProductCategory(createProductCategoryCommand);
        log.info("Product category created: {}", createProductCategoryResponse);
        return ResponseEntity.ok(createProductCategoryResponse);

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UpdateProductCategoryResponse> updateProductCategory(@PathVariable("id") UUID id, @RequestBody UpdateProductCategoryCommand updateProductCategoryCommand) {
        log.info("Updating product category with id: {}", id);
        updateProductCategoryCommand.setId(id);
        UpdateProductCategoryResponse updateProductCategoryResponse = productCategoryApplicationService.updateProductCategory(updateProductCategoryCommand);
        log.info("Product category updated with: {}", updateProductCategoryResponse);
        return ResponseEntity.ok(updateProductCategoryResponse);
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteProductCategoryResponse> deleteProductCategory(@PathVariable("id") UUID id) {
        log.info("Deleting product category with id: {}", id);
        DeleteProductCategoryCommand deleteProductCategoryCommand = DeleteProductCategoryCommand.builder().id(id).build();
        DeleteProductCategoryResponse deleteProductCategoryResponse = productCategoryApplicationService.deleteProductCategory(deleteProductCategoryCommand);
        log.info("Product category deleted with id: {}", id);
        return ResponseEntity.ok(deleteProductCategoryResponse);
    }
}
