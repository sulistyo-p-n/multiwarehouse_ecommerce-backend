package com.multiwarehouse.app.inventory.service.domain.mapper;

import com.multiwarehouse.app.inventory.service.domain.dto.ProductResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDataMapper {
    public ProductResponse productResponseFromProduct(Product product) {
        return ProductResponse.builder()
                .id(product.getId().getValue())
                .categoryId(product.getProductCategoryId().getValue())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice().getAmount())
                .active(product.isActive())
                .build();
    }
}
