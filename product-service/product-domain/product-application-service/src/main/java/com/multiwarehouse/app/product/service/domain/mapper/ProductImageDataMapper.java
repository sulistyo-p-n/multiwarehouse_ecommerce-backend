package com.multiwarehouse.app.product.service.domain.mapper;

import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductImageCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductImageResponse;
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
                .withCode(createProductImageCommand.getCode())
                .withName(createProductImageCommand.getName())
                .withDescription(createProductImageCommand.getDescription())
                .withPath(createProductImageCommand.getPath())
                .withActive(createProductImageCommand.getActive())
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
                .code(productImage.getCode())
                .name(productImage.getName())
                .description(productImage.getDescription())
                .path(productImage.getPath())
                .active(productImage.getActive())
                .build();
    }
}
