package com.multiwarehouse.app.user.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.user.service.domain.exception.UserDomainException;
import com.multiwarehouse.app.user.service.domain.valueobject.UserAdminWarehouseId;

import java.util.UUID;

public class UserAdminWarehouse extends BaseEntity<UserAdminWarehouseId> {
    private UserId userId;
    private final WarehouseId warehouseId;

    public void initialize() {
        setId(new UserAdminWarehouseId(UUID.randomUUID()));
    }

    public void validateInitialization() {
        validateInitialId();
        validateFields();
    }

    public void validateInitialId() {
        if (getId() != null) {
            throw  new UserDomainException("Admin is not in correct state for initialization");
        }
    }

    public void validate() {
        validateId();
        validateFields();
    }

    public void validateId() {
        if (getId() == null ) throw new UserDomainException("Admin Id cannot be null");
    }

    public void validateFields() {
        validateUserId();
        validateWarehouseId();
    }

    public void validateUserId() {
        if (userId == null) throw new UserDomainException("Admin UserId cannot be null");
    }

    public void validateWarehouseId() {
        if (warehouseId == null) throw new UserDomainException("Admin WarehouseId cannot be null");
    }

    public void setUserId(UserId userId) {
        if (userId == null) return;
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
