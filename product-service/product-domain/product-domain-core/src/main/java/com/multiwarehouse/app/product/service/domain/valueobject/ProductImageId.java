package com.multiwarehouse.app.product.service.domain.valueobject;

import com.multiwarehouse.app.domain.valueobject.BaseId;

import java.util.UUID;

public class ProductImageId extends BaseId<UUID> {
    public ProductImageId(UUID value) {
        super(value);
    }
}
