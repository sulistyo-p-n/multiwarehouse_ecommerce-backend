package com.multiwarehouse.app.domain.valueobject;

import java.util.UUID;

public class ProductCategoryId extends BaseId<UUID> {

    public ProductCategoryId(UUID value) {
        super(value);
    }
}
