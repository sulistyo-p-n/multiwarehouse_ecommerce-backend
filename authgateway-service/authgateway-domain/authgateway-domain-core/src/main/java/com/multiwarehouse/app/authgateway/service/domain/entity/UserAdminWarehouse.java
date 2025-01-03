package com.multiwarehouse.app.authgateway.service.domain.entity;

import com.multiwarehouse.app.authgateway.service.domain.valueobject.UserAdminWarehouseId;
import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;

public class UserAdminWarehouse extends BaseEntity<UserAdminWarehouseId> {
    private UserId userId;
    private final WarehouseId warehouseId;

    public void setUserId(UserId userId) {
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    private UserAdminWarehouse(Builder builder) {
        super.setId(builder.id);
        userId = builder.userId;
        warehouseId = builder.warehouseId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UserAdminWarehouseId id;
        private UserId userId;
        private WarehouseId warehouseId;

        private Builder() {
        }

        public Builder withId(UserAdminWarehouseId val) {
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

        public UserAdminWarehouse build() {
            return new UserAdminWarehouse(this);
        }
    }
}
