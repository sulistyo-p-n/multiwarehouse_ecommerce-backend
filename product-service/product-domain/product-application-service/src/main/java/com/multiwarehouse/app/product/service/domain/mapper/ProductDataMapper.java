package com.multiwarehouse.app.product.service.domain.mapper;

import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductsResponse;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductResponse;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDataMapper {
    private final ProductCategoryDataMapper productCategoryDataMapper;
    private final ProductImageDataMapper productImageDataMapper;

    public ProductDataMapper(ProductCategoryDataMapper productCategoryDataMapper, ProductImageDataMapper productImageDataMapper) {
        this.productCategoryDataMapper = productCategoryDataMapper;
        this.productImageDataMapper = productImageDataMapper;
    }

    public Product createProductCommandToProduct(CreateProductCommand createProductCommand) {
        return Product.builder()
                .withCode(createProductCommand.getCode())
                .withName(createProductCommand.getName())
                .withDescription(createProductCommand.getDescription())
                .withPrice(new Money(createProductCommand.getPrice()))
                .withActive(createProductCommand.getActive())
                .withCategory(ProductCategory.builder().withId(new ProductCategoryId(createProductCommand.getCategoryId())).build())
                .withProductImages(productImageDataMapper.createProductImageCommandsToProductImages(createProductCommand.getImages()))
                .build();
    }

    public CreateProductResponse productToCreateProductResponse(Product product) {
        return CreateProductResponse.builder()
                .id(product.getId().getValue())
                .build();
    }

    public Product updateProductCommandToProduct(UpdateProductCommand updateProductCommand) {
        return Product.builder()
                .withId(new ProductId(updateProductCommand.getId()))
                .withCode(updateProductCommand.getCode())
                .withName(updateProductCommand.getName())
                .withDescription(updateProductCommand.getDescription())
                .withPrice(new Money(updateProductCommand.getPrice()))
                .withActive(updateProductCommand.getActive())
                .withCategory(ProductCategory.builder().withId(new ProductCategoryId(updateProductCommand.getCategoryId())).build())
                .build();
    }

    public UpdateProductResponse productToUpdateProductResponse(Product product) {
        return UpdateProductResponse.builder()
                .id(product.getId().getValue())
                .build();
    }

    public GetProductsResponse productsToGetProductsResponse(List<Product> products) {
        return GetProductsResponse.builder()
                .products(products.stream().map(this::productToGetProductResponse).collect(Collectors.toList()))
                .build();
    }

    public GetProductResponse productToGetProductResponse(Product product) {
        return GetProductResponse.builder()
                .id(product.getId().getValue())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .active(product.getActive())
                .category(productCategoryDataMapper.productCategoryToGetProductCategoryResponse(product.getProductCategory()))
                .productImages(productImageDataMapper.productImagesToGetProductImageResponses(product.getProductImages()))
                .build();
    }
}
