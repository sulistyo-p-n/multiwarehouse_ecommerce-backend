package com.multiwarehouse.app.user.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;

public class Warehouse extends AggregateRoot<WarehouseId> {
    private final String code;
    private final String name;
    private final boolean active;

    private final Boolean softDeleted;

    private Warehouse(Builder builder) {
        super.setId(builder.id);
        code = builder.code;
        name = builder.name;
        active = builder.active;
        softDeleted = builder.softDeleted;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return active;
    }

    public Boolean isSoftDeleted() {
        return softDeleted;
    }

    public static final class Builder {
        private WarehouseId id;
        private String code;
        private String name;
        private boolean active;
        private Boolean softDeleted;

        private Builder() {
        }

        public Builder withId(WarehouseId val) {
            id = val;
            return this;
        }

        public Builder withCode(String val) {
            code = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withActive(boolean val) {
            active = val;
            return this;
        }

        public Builder withSoftDeleted(Boolean val) {
            softDeleted = val;
            return this;
        }

        public Warehouse build() {
            return new Warehouse(this);
        }
    }
}
