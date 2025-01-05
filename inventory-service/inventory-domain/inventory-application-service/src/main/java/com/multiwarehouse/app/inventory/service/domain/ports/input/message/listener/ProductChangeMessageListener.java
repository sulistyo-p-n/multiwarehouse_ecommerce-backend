package com.multiwarehouse.app.inventory.service.domain.ports.input.message.listener;

import com.multiwarehouse.app.inventory.service.domain.entity.Product;

public interface ProductChangeMessageListener {
    void productCreated(Product product);
    void productUpdated(Product product);
    void productSoftDeleted(Product product);
    void productHardDeleted(Product product);
}
