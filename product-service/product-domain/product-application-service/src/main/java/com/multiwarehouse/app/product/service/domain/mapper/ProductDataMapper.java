package com.multiwarehouse.app.product.service.domain.mapper;

import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductImageCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductImageResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductImageCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductResponse;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDataMapper {
    private final ProductCategoryDataMapper productCategoryDataMapper;

    public ProductDataMapper(ProductCategoryDataMapper productCategoryDataMapper) {
        this.productCategoryDataMapper = productCategoryDataMapper;
    }

    public Product productFromCreateProductCommand(CreateProductCommand createProductCommand) {
        return Product.builder()
                .withCode(createProductCommand.getCode())
                .withName(createProductCommand.getName())
                .withDescription(createProductCommand.getDescription())
                .withPrice(new Money(createProductCommand.getPrice()))
                .withActive(createProductCommand.getActive())
                .withCategory(ProductCategory.builder().withId(new ProductCategoryId(createProductCommand.getCategoryId())).build())
                .withImages(this.productImagesFromCreateProductImageCommands(createProductCommand.getImages()))
                .build();
    }

    private List<ProductImage> productImagesFromCreateProductImageCommands(List<CreateProductImageCommand> createProductImageCommands) {
        return createProductImageCommands.stream()
                .map(this::productImageFromCreateProductImageCommand)
                .collect(Collectors.toList());
    }
    private ProductImage productImageFromCreateProductImageCommand(CreateProductImageCommand createProductImageCommand) {
        return ProductImage.builder()
                .withName(createProductImageCommand.getName())
                .withDescription(createProductImageCommand.getDescription())
                .withPath(createProductImageCommand.getPath())
                .withFront(createProductImageCommand.getFront())
                .withActive(createProductImageCommand.getActive())
                .build();
    }

    public CreateProductResponse createProductResponseFromProduct(Product product) {
        return CreateProductResponse.builder()
                .id(product.getId().getValue())
                .build();
    }

    public Product productFromUpdateProductCommand(UpdateProductCommand updateProductCommand) {
        return Product.builder()
                .withId(new ProductId(updateProductCommand.getId()))
                .withCode(updateProductCommand.getCode())
                .withName(updateProductCommand.getName())
                .withDescription(updateProductCommand.getDescription())
                .withPrice(updateProductCommand.getPrice() == null ? null : new Money(updateProductCommand.getPrice()))
                .withActive(updateProductCommand.getActive())
                .withCategory(updateProductCommand.getCategoryId() == null ? null : ProductCategory.builder().withId(new ProductCategoryId(updateProductCommand.getCategoryId())).build())
                .withImages(updateProductCommand.getCategoryId() == null ? null : this.productImagesFromUpdateProductImageCommands(updateProductCommand.getImages()))
                .build();
    }

    private List<ProductImage> productImagesFromUpdateProductImageCommands(List<UpdateProductImageCommand> updateProductImageCommands) {
        return updateProductImageCommands.stream()
                .map(this::productImageFromUpdateProductImageCommand)
                .collect(Collectors.toList());
    }

    private ProductImage productImageFromUpdateProductImageCommand(UpdateProductImageCommand updateProductImageCommands) {
        return ProductImage.builder()
                .withName(updateProductImageCommands.getName())
                .withDescription(updateProductImageCommands.getDescription())
                .withPath(updateProductImageCommands.getPath())
                .withFront(updateProductImageCommands.getFront())
                .withActive(updateProductImageCommands.getActive())
                .build();
    }

    public UpdateProductResponse updateProductResponseFromProduct(Product product) {
        return UpdateProductResponse.builder()
                .id(product.getId().getValue())
                .build();
    }

    public GetProductResponse getProductResponseFromProduct(Product product) {
        return GetProductResponse.builder()
                .id(product.getId().getValue())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .active(product.isActive())
                .category(productCategoryDataMapper.getProductCategoryResponseFromProductCategory(product.getCategory()))
                .images(this.getProductImageResponsesFromProductImages(product.getImages()))
                .quantity(product.getQuantity())
                .softDeleted(product.isSoftDeleted())
                .build();
    }

    private List<GetProductImageResponse> getProductImageResponsesFromProductImages(List<ProductImage> productImages) {
        return productImages.stream()
                .map(this::getProductImageResponseFromProductImage)
                .collect(Collectors.toList());
    }

    private GetProductImageResponse getProductImageResponseFromProductImage(ProductImage productImage) {
        return GetProductImageResponse.builder()
                .id(productImage.getId().getValue())
                .name(productImage.getName())
                .description(productImage.getDescription())
                .path(productImage.getPath())
                .front(productImage.isFront())
                .active(productImage.isActive())
                .build();
    }
}
