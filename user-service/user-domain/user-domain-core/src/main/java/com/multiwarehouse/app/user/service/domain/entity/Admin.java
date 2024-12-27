package com.multiwarehouse.app.user.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.user.service.domain.valuobject.AdminId;

public class Admin extends BaseEntity<AdminId> {
    private final UserId userId;
    private final WarehouseId warehouseId;

    public UserId getUserId() {
        return userId;
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    private Admin(Builder builder) {
        super.setId(builder.id);
        userId = builder.userId;
        warehouseId = builder.warehouseId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private AdminId id;
        private UserId userId;
        private WarehouseId warehouseId;

        private Builder() {
        }

        public Builder withId(AdminId val) {
            id = val;
            return this;
        }

        public Builder withUserId(UserId val) {
            userId = val;
            return this;
        }

        public Builder withWarehouseId(WarehouseId val) {
            warehouseId = val;
            return this;
        }

        public Admin build() {
            return new Admin(this);
        }
    }
}
