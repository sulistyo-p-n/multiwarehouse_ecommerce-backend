package com.multiwarehouse.app.product.service.domain.mapper;

import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductImageCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductResponse;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductResponse;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDataMapper {

    public Product createProductCommandToProduct(CreateProductCommand createProductCommand) {
        return Product.builder()
                .withCode(createProductCommand.getCode())
                .withName(createProductCommand.getName())
                .withDesc(createProductCommand.getDesc())
                .withPrice(new Money(createProductCommand.getPrice()))
                .withActive(createProductCommand.getActive())
                .withCategory(ProductCategory.builder().withId(new ProductCategoryId(createProductCommand.getCategoryId())).build())
                .withProductImages(createProductImageCommandsToProductImages(createProductCommand.getCreateProductImageCommands()))
                .build();
    }

    private List<ProductImage> createProductImageCommandsToProductImages(List<CreateProductImageCommand> createProductImageCommands) {
        return createProductImageCommands.stream()
                .map(createProductImageCommand ->
                        ProductImage.builder()
                                .withCode(createProductImageCommand.getCode())
                                .withName(createProductImageCommand.getName())
                                .withDesc(createProductImageCommand.getDesc())
                                .withPath(createProductImageCommand.getPath())
                                .withActive(createProductImageCommand.getActive())
                                .build()
                ).collect(Collectors.toList());
    }

    public CreateProductResponse productToCreateProductResponse(Product product) {
        return CreateProductResponse.builder()
                .id(product.getId().getValue())
                .build();
    }

    public Product deleteProductCommandToProduct(DeleteProductCommand deleteProductCommand) {
        return Product.builder()
                .withId(new ProductId(deleteProductCommand.getId()))
                .build();
    }

    public DeleteProductResponse productToDeleteProductResponse(Product product) {
        return DeleteProductResponse.builder()
                .id(product.getId().getValue())
                .build();
    }
}
