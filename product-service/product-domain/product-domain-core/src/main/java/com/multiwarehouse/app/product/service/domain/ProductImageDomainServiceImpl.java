package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.constant.DomainConstants;
import com.multiwarehouse.app.domain.event.publisher.DomainEventPublisher;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.entity.ProductImage;
import com.multiwarehouse.app.product.service.domain.event.ProductCreatedEvent;
import com.multiwarehouse.app.product.service.domain.event.ProductDeletedEvent;
import com.multiwarehouse.app.product.service.domain.event.ProductUpdatedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ProductImageDomainServiceImpl implements ProductImageDomainService {
    @Override
    public ProductImage validateAndInitializeProductImage(ProductImage productImage) {
        productImage.validateInitialization();
        productImage.initialize();
        return productImage;
    }

    @Override
    public ProductImage validateAndSetProductImage(ProductImage productImage, ProductImage newProductImage) {
        productImage.validateId();
        productImage.setProductId(newProductImage.getProductId());
        productImage.setCode(newProductImage.getCode());
        productImage.setName(newProductImage.getName());
        productImage.setDescription(newProductImage.getDescription());
        productImage.setActive(newProductImage.getActive());
        return productImage;
    }
}
