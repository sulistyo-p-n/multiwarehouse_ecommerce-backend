package com.multiwarehouse.app.product.service.domain.mapper;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductImageCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductImageResponse;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductImageCommand;
import com.multiwarehouse.app.product.service.domain.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductImageDataMapper {

    public List<ProductImage> createProductImageCommandsToProductImages(List<CreateProductImageCommand> createProductImageCommands) {
        return createProductImageCommands.stream()
                .map(this::createProductImageCommandToProductImage)
                .collect(Collectors.toList());
    }
    public ProductImage createProductImageCommandToProductImage(CreateProductImageCommand createProductImageCommand) {
        return ProductImage.builder()
                .withName(createProductImageCommand.getName())
                .withDescription(createProductImageCommand.getDescription())
                .withPath(createProductImageCommand.getPath())
                .withActive(createProductImageCommand.getActive())
                .build();
    }

    public List<ProductImage> updateProductImageCommandsToProductImages(List<UpdateProductImageCommand> updateProductImageCommands) {
        return updateProductImageCommands.stream()
                .map(this::updateProductImageCommandToProductImage)
                .collect(Collectors.toList());
    }
    public ProductImage updateProductImageCommandToProductImage(UpdateProductImageCommand updateProductImageCommands) {
        return ProductImage.builder()
                .withName(updateProductImageCommands.getName())
                .withDescription(updateProductImageCommands.getDescription())
                .withPath(updateProductImageCommands.getPath())
                .withActive(updateProductImageCommands.getActive())
                .build();
    }

    public List<GetProductImageResponse> productImagesToGetProductImageResponses(List<ProductImage> productImages) {
        return productImages.stream()
                .map(this::productImageToGetProductImageResponse)
                .collect(Collectors.toList());
    }

    public GetProductImageResponse productImageToGetProductImageResponse(ProductImage productImage) {
        return GetProductImageResponse.builder()
                .id(productImage.getId().getValue())
                .name(productImage.getName())
                .description(productImage.getDescription())
                .path(productImage.getPath())
                .active(productImage.getActive())
                .build();
    }
}
