package com.multiwarehouse.app.product.service.dataaccess.product.mapper;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductImageEntity;
import com.multiwarehouse.app.product.service.domain.entity.ProductImage;
import com.multiwarehouse.app.product.service.domain.valueobject.ProductImageId;
import org.springframework.stereotype.Component;

@Component
public class ProductImageDataAccessMapper {

    public ProductImage productImageEntityToProductImage(ProductImageEntity productImageEntity) {
        return ProductImage.builder()
                .withId(new ProductImageId(productImageEntity.getId()))
                .withName(productImageEntity.getName())
                .withDescription(productImageEntity.getDescription())
                .withPath(productImageEntity.getPath())
                .withActive(productImageEntity.getActive())
                .build();
    }

    public ProductImageEntity productImageToProductImageEntity(ProductImage productImage) {
        return ProductImageEntity.builder()
                .id(productImage.getId().getValue())
                .name(productImage.getName())
                .description(productImage.getDescription())
                .path(productImage.getPath())
                .active(productImage.getActive())
                .build();
    }
}
