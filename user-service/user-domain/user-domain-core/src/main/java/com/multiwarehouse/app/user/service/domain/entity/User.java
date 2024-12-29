package com.multiwarehouse.app.user.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.*;
import com.multiwarehouse.app.user.service.domain.exception.UserDomainException;

import java.util.List;
import java.util.UUID;

public class User extends AggregateRoot<UserId> {
    private String username;
    private String email;
    private String password;
    private Boolean active;
    private Boolean enable;
    private UserRole role;
    private UserAdminWarehouse userAdminWarehouse;
    private UserProfile userProfile;
    private List<UserAddress> userAddresses;

    private final Boolean isSoftDeleted;

    public void initialize() {
        setId(new UserId(UUID.randomUUID()));
        initializeUserProfile();
        initializeUserAddresses();
        initializeUserAdminWarehouse();
    }

    private void initializeUserProfile() {
        if (getUserProfile() == null) return;
        userProfile.initialize();
        userProfile.setUserId(getId());
    }

    private void initializeUserAddresses() {
        if (getUserAddresses() == null) return;
        for (UserAddress userAddress : userAddresses) {
            userAddress.initialize();
            userAddress.setUserId(getId());
        }
    }

    private void initializeUserAdminWarehouse() {
        if (getUserAdminWarehouse() == null) return;
        userAdminWarehouse.initialize();
        userAdminWarehouse.setUserId(getId());
    }

    public void validateInitialization() {
        validateInitialId();
        validateFields();
    }

    public void validateInitialId() {
        if (getId() != null) {
            throw new UserDomainException("User is not in correct state for initialization");
        }
    }

    public void validate() {
        validateId();
        validateFields();
    }

    public void validateId() {
        if (getId() == null ) throw new UserDomainException("User Id cannot be empty");
    }

    public void validateFields() {
        validateUsername();
        validateEmail();
        validatePassword();
        validateActive();
        validateEnable();
        validateRole();
    }

    private void validateUsername() {
        if (getUsername() == null || getUsername().isEmpty()) throw new UserDomainException("User Username cannot be empty");
    }

    private void validateEmail() {
        if (getEmail() == null || getEmail().isEmpty()) throw new UserDomainException("Product Email cannot be empty");
    }

    private void validatePassword() {
        if (getPassword() == null || getPassword().isEmpty()) throw new UserDomainException("User Password cannot be empty");
    }

    private void validateActive() {
        if (getActive() == null) throw new UserDomainException("User Active cannot be null");
    }

    private void validateEnable() {
        if (getEnable() == null) throw new UserDomainException("User Enable cannot be null");
    }

    private void validateRole() {
        if (getRole() == null) throw new UserDomainException("User Role cannot be null");
    }


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

    public void setUserAdminWarehouse(UserAdminWarehouse userAdminWarehouse) {
        if (userAdminWarehouse == null) return;
        this.userAdminWarehouse = userAdminWarehouse;
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

    public UserAdminWarehouse getUserAdminWarehouse() {
        return userAdminWarehouse;
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
        userAdminWarehouse = builder.admin;
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
        private UserAdminWarehouse admin;
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

        public Builder withUserAdminWarehouse(UserAdminWarehouse val) {
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
