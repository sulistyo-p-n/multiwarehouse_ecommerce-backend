package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductResponse;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.mapper.ProductDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class ProductGetCommandHandler {
    private final ProductDataMapper productDataMapper;
    private final ProductHelper productHelper;

    public ProductGetCommandHandler(ProductDataMapper productDataMapper, ProductHelper productHelper) {
        this.productDataMapper = productDataMapper;
        this.productHelper = productHelper;
    }

    @Transactional(readOnly = true)
    public GetProductResponse getProduct(GetProductCommand getProductCommand) {
        ProductId productId = new ProductId(getProductCommand.getId());
        Product product = productHelper.findProductById(productId);
        log.info("Product is selected with id: {}", product.getId().getValue());
        return productDataMapper.productToGetProductResponse(product);
    }
}
