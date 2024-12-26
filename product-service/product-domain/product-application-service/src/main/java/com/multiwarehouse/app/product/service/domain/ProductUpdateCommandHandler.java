package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductResponse;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.event.ProductUpdatedEvent;
import com.multiwarehouse.app.product.service.domain.mapper.ProductDataMapper;
import com.multiwarehouse.app.product.service.domain.ports.output.message.publisher.product.ProductUpdatedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProductUpdateCommandHandler {
    private final ProductDomainService productDomainService;
    private final ProductDataMapper productDataMapper;
    private final ProductHelper productHelper;
    private final ProductCategoryHelper productCategoryHelper;
    private final ProductUpdatedMessagePublisher productUpdatedMessagePublisher;

    public ProductUpdateCommandHandler(ProductDomainService productDomainService, ProductDataMapper productDataMapper, ProductHelper productHelper, ProductCategoryHelper productCategoryHelper, ProductUpdatedMessagePublisher productUpdatedMessagePublisher) {
        this.productDomainService = productDomainService;
        this.productDataMapper = productDataMapper;
        this.productHelper = productHelper;
        this.productCategoryHelper = productCategoryHelper;
        this.productUpdatedMessagePublisher = productUpdatedMessagePublisher;
    }

    @Transactional
    public UpdateProductResponse updateProduct(UpdateProductCommand updateProductCommand) {
        ProductId productId = new ProductId(updateProductCommand.getId());
        Product product = productHelper.findProductById(productId);
        Product newProduct = productDataMapper.updateProductCommandToProduct(updateProductCommand);
        ProductCategory productCategory = productCategoryHelper.findProductCategoryById(new ProductCategoryId(updateProductCommand.getCategoryId()));
        newProduct.setProductCategory(productCategory);
        ProductUpdatedEvent productUpdatedEvent = productDomainService.validateAndSetProduct(product, newProduct, productUpdatedMessagePublisher);
        Product productSaved = productHelper.saveProduct(productUpdatedEvent.getProduct());
        log.info("Product is updated with id: {}", productSaved.getId().getValue());
        return productDataMapper.productToUpdateProductResponse(productSaved);
    }
}
