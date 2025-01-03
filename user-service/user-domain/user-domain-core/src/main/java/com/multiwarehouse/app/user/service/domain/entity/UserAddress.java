package com.multiwarehouse.app.user.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.user.service.domain.exception.UserDomainException;
import com.multiwarehouse.app.user.service.domain.valueobject.UserAddressId;

import java.util.UUID;

public class UserAddress extends BaseEntity<UserAddressId> {
    private UserId userId;
    private final Address address;

    public void initialize() {
        setId(new UserAddressId(UUID.randomUUID()));
    }

    public void validateInitialization() {
        validateInitialId();
        validateFields();
    }

    public void validateInitialId() {
        if (getId() != null) {
            throw  new UserDomainException("UserAddress is not in correct state for initialization");
        }
    }

    public void validate() {
        validateId();
        validateFields();
    }

    public void validateId() {
        if (getId() == null ) throw new UserDomainException("UserAddress Id cannot be null");
    }

    public void validateFields() {
        validateUserId();
        validateAddress();
    }

    public void validateUserId() {
        if (userId == null) throw new UserDomainException("UserAddress UserId cannot be null");
    }

    public void validateAddress() {
        if (address == null) throw new UserDomainException("UserAddress Address cannot be null");
    }

    public void setUserId(UserId userId) {
        if (userId == null) return;
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public Address getAddress() {
        return address;
    }

    private UserAddress(Builder builder) {
        super.setId(builder.id);
        userId = builder.userId;
        address = builder.address;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private UserAddressId id;
        private UserId userId;
        private Address address;

        private Builder() {
        }

        public Builder withId(UserAddressId val) {
            id = val;
            return this;
        }

        public Builder withUserId(UserId val) {
            userId = val;
            return this;
        }

        public Builder withAddress(Address val) {
            address = val;
            return this;
        }

        public UserAddress build() {
            return new UserAddress(this);
        }
    }
}
