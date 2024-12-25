package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.entity.ProductImage;

public interface ProductImageDomainService {
    ProductImage validateAndInitializeProductImage(ProductImage productImage);
    ProductImage validateAndSetProductImage(ProductImage productImage, ProductImage newProductImage);
}
