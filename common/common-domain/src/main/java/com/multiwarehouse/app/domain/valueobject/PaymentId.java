package com.multiwarehouse.app.domain.valueobject;

import java.util.UUID;

public class PaymentId extends BaseId<UUID> {

    public PaymentId(UUID value) {
        super(value);
    }
}
