package com.multiwarehouse.app.user.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;

import java.util.List;

public class User extends AggregateRoot<UserId> {
    private final UserRole role;
    private final WarehouseId warehouseId;
    private final String username;
    private final String email;
    private final String password;
    private final Boolean isVerified;
    private final List<Address> addresses;

    private User(Builder builder) {
        super.setId(builder.id);
        role = builder.role;
        warehouseId = builder.warehouseId;
        username = builder.username;
        email = builder.email;
        password = builder.password;
        isVerified = builder.isVerified;
        addresses = builder.addresses;
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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public List<Address> getAddresses() {
        return addresses;
    }


    public static final class Builder {
        private UserId id;
        private UserRole role;
        private WarehouseId warehouseId;
        private String username;
        private String email;
        private String password;
        private Boolean isVerified;
        private List<Address> addresses;

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

        public Builder withUsername(String val) {
            username = val;
            return this;
        }

        public Builder withEmail(String val) {
            email = val;
            return this;
        }

        public Builder withPassword(String val) {
            password = val;
            return this;
        }

        public Builder withIsVerified(Boolean val) {
            isVerified = val;
            return this;
        }

        public Builder withAddresses(List<Address> val) {
            addresses = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
