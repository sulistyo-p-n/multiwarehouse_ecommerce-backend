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

public class ProductCategoryDomainServiceImpl implements ProductCategoryDomainService {
    @Override
    public ProductCategory validateAndInitiateCategory(ProductCategory category) {
        category.validationInitialCategory();
        category.initializeCategory();
        return category;
    }

    @Override
    public ProductCategory validateAndUpdateCategory(ProductCategory productCategory, ProductCategory newProductCategory) {
        productCategory.setCode(newProductCategory.getCode());
        productCategory.setName(newProductCategory.getName());
        productCategory.setDescription(newProductCategory.getDescription());
        productCategory.setActive(newProductCategory.getActive());
        return productCategory;
    }
}
