package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductResponse;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.event.ProductCreatedEvent;
import com.multiwarehouse.app.product.service.domain.mapper.ProductDataMapper;
import com.multiwarehouse.app.product.service.domain.ports.output.message.publisher.product.ProductCreatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProductCreateCommandHandler {

    private final ProductDomainService productDomainService;
    private final ProductDataMapper productDataMapper;
    private final ProductHelper productHelper;
    private final ProductCategoryHelper productCategoryHelper;
    private final ProductCreatedMessagePublisher productCreatedMessagePublisher;

    public ProductCreateCommandHandler(ProductDomainService productDomainService, ProductDataMapper productDataMapper, ProductHelper productHelper, ProductCategoryHelper productCategoryHelper, ProductCreatedMessagePublisher productCreatedMessagePublisher) {
        this.productDomainService = productDomainService;
        this.productDataMapper = productDataMapper;
        this.productHelper = productHelper;
        this.productCategoryHelper = productCategoryHelper;
        this.productCreatedMessagePublisher = productCreatedMessagePublisher;
    }

    @Transactional
    public CreateProductResponse createProduct(CreateProductCommand createProductCommand) {
        Product product = productDataMapper.productFromCreateProductCommand(createProductCommand);
        ProductCategory productCategory = productCategoryHelper.findProductCategoryById(new ProductCategoryId(createProductCommand.getCategoryId()));
        product.setCategory(productCategory);
        ProductCreatedEvent productCreatedEvent = productDomainService.validateAndInitializeProduct(product, productCreatedMessagePublisher);
        Product productSaved = productHelper.saveProduct(productCreatedEvent.getProduct());
        log.info("Product is created with id: {}", productSaved.getId().getValue());
        productCreatedMessagePublisher.publish(productCreatedEvent);
        return productDataMapper.createProductResponseFromProduct(productSaved);
    }
}
