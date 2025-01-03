package com.multiwarehouse.app.warehouse.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.exception.WarehouseDomainException;

import java.util.List;
import java.util.UUID;

public class Warehouse extends AggregateRoot<WarehouseId> {
    private String code;
    private String name;
    private String description;
    private Address address;
    private Boolean active;

    private final Boolean softDeleted;

    private Warehouse(Builder builder) {
        super.setId(builder.id);
        code = builder.code;
        name = builder.name;
        description = builder.description;
        address = builder.address;
        active = builder.active;
        softDeleted = builder.softDeleted;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initialize() {
        setId(new WarehouseId(UUID.randomUUID()));
    }

    public void validateInitialization() {
        validateInitialId();
        validateFields();
    }

    public void validateInitialId() {
        if (getId() != null) {
            throw new WarehouseDomainException("Warehouse is not in correct state for initialization");
        }
    }

    public void validate() {
        validateId();
        validateFields();
    }

    public void validateId() {
        if (getId() == null ) throw new WarehouseDomainException("Warehouse Id cannot be empty");
    }

    public void validateFields() {
        validateCode();
        validateName();
        validateActive();
    }

    private void validateCode() {
        if (code == null || getCode().isEmpty()) throw new WarehouseDomainException("Warehouse Code cannot be empty");
    }

    private void validateName() {
        if (name == null || getName().isEmpty()) throw new WarehouseDomainException("Warehouse cannot be empty");
    }

    private void validateAddress() {
        if (address == null) throw new WarehouseDomainException("Warehouse Address cannot be null");
    }

    private void validateActive() {
        if (active == null) throw new WarehouseDomainException("Warehouse Active cannot be null");
    }

    public void setCode(String value) {
        if (value == null) return;
        if (value.isEmpty()) throw  new WarehouseDomainException("Warehouse Code cannot be empty");
        code = value;
    }

    public void setName(String value) {
        if (value == null) return;
        if (value.isEmpty()) throw  new WarehouseDomainException("Warehouse Name cannot be empty");
        name = value;
    }

    public void setDescription(String value) {
        if (value == null) return;
        description = value;
    }

    public void setAddress(Address value) {
        if (value == null) return;
        address = value;
    }

    public void setActive(Boolean value) {
        if (value == null) return;
        active = value;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Address getAddress() {
        return address;
    }

    public Boolean isActive() {
        return active;
    }

    public Boolean isSoftDeleted() {
        return softDeleted;
    }

    public static final class Builder {
        private WarehouseId id;
        private String code;
        private String name;
        private String description;
        private Address address;
        private Boolean active;
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

        public Builder withDescription(String val) {
            description = val;
            return this;
        }

        public Builder withAddress(Address val) {
            address = val;
            return this;
        }

        public Builder withActive(Boolean val) {
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
