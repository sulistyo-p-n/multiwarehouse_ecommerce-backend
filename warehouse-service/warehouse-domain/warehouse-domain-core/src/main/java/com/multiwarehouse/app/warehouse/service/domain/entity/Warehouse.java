package com.multiwarehouse.app.warehouse.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.exception.WarehouseDomainException;

import java.util.List;
import java.util.UUID;

public class Warehouse extends AggregateRoot<WarehouseId> {
    private final String name;
    private final Address address;
    private final List<Product> products; // TODO relation to ProductEntity

    public void initializeWarehouse() {
        setId(new WarehouseId(UUID.randomUUID()));
    }

    public void validationInitialWarehouse() {
        if (getId() != null) {
            throw  new WarehouseDomainException("Warehouse is not in correct state for initialization");
        }
    }

    private Warehouse(Builder builder) {
        super.setId(builder.id);
        name = builder.name;
        address = builder.address;
        products = builder.products;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public List<Product> getProducts() {
        return products;
    }

    public static final class Builder {
        private WarehouseId id;
        private String name;
        private Address address;
        private List<Product> products;

        private Builder() {
        }

        public Builder withId(WarehouseId val) {
            id = val;
            return this;
        }

        public Builder withName(String val) {
            name = val;
            return this;
        }

        public Builder withAddress(Address val) {
            address = val;
            return this;
        }

        public Builder withProducts(List<Product> val) {
            products = val;
            return this;
        }

        public Warehouse build() {
            return new Warehouse(this);
        }
    }
}
