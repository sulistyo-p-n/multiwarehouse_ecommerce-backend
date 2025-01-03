package com.multiwarehouse.app.authgateway.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.UserRole;

public class User extends AggregateRoot<UserId> {
    private final String username;
    private final String email;
    private final String password;
    private final Boolean active;
    private final Boolean enable;
    private final UserRole role;
    private final UserAdminWarehouse userAdminWarehouse;

    private final Boolean isSoftDeleted;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getActive() {
        return active;
    }

    public Boolean getEnable() {
        return enable;
    }

    public UserRole getRole() {
        return role;
    }

    public UserAdminWarehouse getUserAdminWarehouse() {
        return userAdminWarehouse;
    }

    public Boolean getSoftDeleted() {
        return isSoftDeleted;
    }

    private User(Builder builder) {
        super.setId(builder.id);
        username = builder.username;
        email = builder.email;
        password = builder.password;
        active = builder.active;
        enable = builder.enable;
        role = builder.role;
        userAdminWarehouse = builder.admin;
        isSoftDeleted = builder.isSoftDeleted;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UserId id;
        private String username;
        private String email;
        private String password;
        private Boolean active;
        private Boolean enable;
        private UserRole role;
        private UserAdminWarehouse admin;
        private Boolean isSoftDeleted;

        private Builder() {
        }

        public Builder withId(UserId val) {
            id = val;
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

        public Builder withActive(Boolean val) {
            active = val;
            return this;
        }

        public Builder withEnable(Boolean val) {
            enable = val;
            return this;
        }

        public Builder withRole(UserRole val) {
            role = val;
            return this;
        }

        public Builder withUserAdminWarehouse(UserAdminWarehouse val) {
            admin = val;
            return this;
        }

        public Builder withIsSoftDeleted(Boolean val) {
            isSoftDeleted = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
