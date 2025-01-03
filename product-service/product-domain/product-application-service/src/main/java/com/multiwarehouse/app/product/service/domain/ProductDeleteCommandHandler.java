package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductResponse;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.event.ProductDeletedEvent;
import com.multiwarehouse.app.product.service.domain.ports.output.message.publisher.product.ProductDeletedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProductDeleteCommandHandler {
    private final ProductDomainService productDomainService;
    private final ProductHelper productHelper;
    private final ProductDeletedMessagePublisher productDeletedMessagePublisher;

    public ProductDeleteCommandHandler(ProductDomainService productDomainService, ProductHelper productHelper, ProductDeletedMessagePublisher productDeletedMessagePublisher) {
        this.productDomainService = productDomainService;
        this.productHelper = productHelper;
        this.productDeletedMessagePublisher = productDeletedMessagePublisher;
    }

    @Transactional
    public DeleteProductResponse deleteProduct(DeleteProductCommand deleteProductCommand) {
        ProductId productId = new ProductId(deleteProductCommand.getId());
        Product product = productHelper.findProductById(productId);
        ProductDeletedEvent productDeletedEvent = productDomainService.validateAndRemoveProduct(product, productDeletedMessagePublisher);
        productHelper.deleteProduct(productDeletedEvent.getProduct(),deleteProductCommand.getForceDelete());
        log.info("Product is deleted with id: {}", productDeletedEvent.getProduct().getId().getValue());
        return  DeleteProductResponse.builder().id(productDeletedEvent.getProduct().getId().getValue()).build();
    }
}
