package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.product.service.domain.dto.get.GetProductsCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductsResponse;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.mapper.ProductDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class ProductsGetCommandHandler {
    private final ProductDataMapper productDataMapper;
    private final ProductHelper productHelper;

    public ProductsGetCommandHandler(ProductDataMapper productDataMapper, ProductHelper productHelper) {
        this.productDataMapper = productDataMapper;
        this.productHelper = productHelper;
    }

    @Transactional(readOnly = true)
    public GetProductsResponse getProducts(GetProductsCommand getProductsCommand) {
        List<Product> products = productHelper.findProducts(getProductsCommand);
        log.info("Products is selected with total: {}", products.size());
        return productDataMapper.productsToGetProductsResponse(products);
    }
}
