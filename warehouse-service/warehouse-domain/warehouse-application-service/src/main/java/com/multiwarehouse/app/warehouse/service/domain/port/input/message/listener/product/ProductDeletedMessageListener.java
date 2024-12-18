package com.multiwarehouse.app.warehouse.service.domain.port.input.message.listener.product;

import com.multiwarehouse.app.warehouse.service.domain.dto.message.product.ProductResponse;

public interface ProductDeletedMessageListener {

    void productUpdated(ProductResponse productResponse);
}
