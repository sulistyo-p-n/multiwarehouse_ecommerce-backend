package com.multiwarehouse.app.warehouse.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.exception.WarehouseUnauthorizedException;

public class User extends AggregateRoot<UserId> {
    private final UserRole role;
    private final WarehouseId warehouseId;

    private User(Builder builder) {
        super.setId(builder.id);
        role = builder.role;
        warehouseId = builder.warehouseId;
    }

    public void validateUserCanManageWarehouse(WarehouseId warehouseId) {
        if (!isCanManageWarehouse(warehouseId)) throw new WarehouseUnauthorizedException("Access denied. User is not authorized to do this action.");
    }

    public void validateUserIsSuperAdmin() {
        if (!isSuperAdmin()) throw new WarehouseUnauthorizedException("Access denied. User is not authorized to do this action.");
    }

    private boolean isCanManageWarehouse(WarehouseId warehouseId) {
        return isSuperAdmin() || isWarehouseAdminOf(warehouseId);
    }

    private boolean isSuperAdmin() {
        return this.role.equals(UserRole.SUPER_ADMIN);
    }

    private boolean isWarehouseAdminOf(WarehouseId warehouseId) {
        return this.role.equals(UserRole.WAREHOUSE_ADMIN) && this.warehouseId.equals(warehouseId);
    }

    public static Builder builder() {
        return new Builder();
    }

    public UserRole getRole() {
        return role;
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public static final class Builder {
        private UserId id;
        private UserRole role;
        private WarehouseId warehouseId;

        private Builder() {
        }

        public Builder withId(UserId val) {
            id = val;
            return this;
        }

        public Builder withRole(UserRole val) {
            role = val;
            return this;
        }

        public Builder withWarehouseId(WarehouseId val) {
            warehouseId = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
