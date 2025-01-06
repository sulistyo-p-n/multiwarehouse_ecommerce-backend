package com.multiwarehouse.app.product.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Inventory extends AggregateRoot<InventoryId> {
    private final WarehouseId warehouseId;
    private final List<InventoryStock> stocks;
    private final Boolean active;

    private Inventory(Builder builder) {
        super.setId(builder.id);
        warehouseId = builder.warehouseId;
        stocks = builder.stocks;
        active = builder.active;
    }

    public static Builder builder() {
        return new Builder();
    }

    public WarehouseId getWarehouseId() {
        return warehouseId;
    }

    public List<InventoryStock> getStocks() {
        return stocks;
    }

    public Boolean isActive() {
        return active;
    }

    public static final class Builder {
        private InventoryId id;
        private WarehouseId warehouseId;
        private List<InventoryStock> stocks;
        private Boolean active;

        private Builder() {
        }

        public Builder withId(InventoryId val) {
            id = val;
            return this;
        }

        public Builder withWarehouseId(WarehouseId val) {
            warehouseId = val;
            return this;
        }

        public Builder withStocks(List<InventoryStock> val) {
            stocks = val;
            return this;
        }

        public Builder withActive(Boolean val) {
            active = val;
            return this;
        }

        public Inventory build() {
            return new Inventory(this);
        }
    }
}
