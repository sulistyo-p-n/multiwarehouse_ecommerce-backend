package com.multiwarehouse.app.user.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.user.service.domain.exception.UserDomainException;

import java.util.List;

public class User extends AggregateRoot<UserId> {
    private String username;
    private String email;
    private String password;
    private Boolean active;
    private Boolean enable;
    private UserRole role;
    private Admin admin;
    private UserProfile userProfile;
    private List<UserAddress> userAddresses;
    private final Boolean isSoftDeleted;

    public void setUsername(String username) {
        if (username == null) return;
        if (username.isEmpty()) throw  new UserDomainException("User Username cannot be empty");
        this.username = username;
    }

    public void setEmail(String email) {
        if (email == null) return;
        if (email.isEmpty()) throw  new UserDomainException("User Email cannot be empty");
        this.email = email;
    }

    public void setPassword(String password) {
        if (password == null) return;
        if (password.isEmpty()) throw  new UserDomainException("User Password cannot be empty");
        this.password = password;
    }

    public void setActive(Boolean active) {
        if (active == null) return;
        this.active = active;
    }

    public void setEnable(Boolean enable) {
        if (enable == null) return;
        this.enable = enable;
    }

    public void setRole(UserRole role) {
        if (role == null) return;
        this.role = role;
    }

    public void setAdmin(Admin admin) {
        if (admin == null) return;
        this.admin = admin;
    }

    public void setUserProfile(UserProfile userProfile) {
        if (userProfile == null) return;
        this.userProfile = userProfile;
    }

    public void setUserAddresses(List<UserAddress> userAddresses) {
        if (userAddresses == null) return;
        this.userAddresses = userAddresses;
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

    public Boolean getActive() {
        return active;
    }

    public Boolean getEnable() {
        return enable;
    }

    public UserRole getRole() {
        return role;
    }

    public Admin getAdmin() {
        return admin;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public List<UserAddress> getUserAddresses() {
        return userAddresses;
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
        admin = builder.admin;
        userProfile = builder.userProfile;
        userAddresses = builder.userAddresses;
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
        private Admin admin;
        private UserProfile userProfile;
        private List<UserAddress> userAddresses;
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

        public Builder withAdmin(Admin val) {
            admin = val;
            return this;
        }

        public Builder withUserProfile(UserProfile val) {
            userProfile = val;
            return this;
        }

        public Builder withUserAddresses(List<UserAddress> val) {
            userAddresses = val;
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
