package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.ports.input.message.listener.ProductChangeMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class ProductChangeMessageListenerImpl implements ProductChangeMessageListener {
    private final ProductHelper productHelper;

    public ProductChangeMessageListenerImpl(ProductHelper productHelper) {
        this.productHelper = productHelper;
    }

    @Override
    public void productCreated(Product product) {
        productHelper.saveProduct(product);
    }

    @Override
    public void productUpdated(Product product) {
        productHelper.saveProduct(product);
    }

    @Override
    public void productSoftDeleted(Product product) {
        productHelper.deleteProduct(product, false);
    }

    @Override
    public void productHardDeleted(Product product) {
        productHelper.deleteProduct(product, true);
    }
}
