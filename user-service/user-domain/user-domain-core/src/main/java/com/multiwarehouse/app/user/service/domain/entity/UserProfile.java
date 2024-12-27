package com.multiwarehouse.app.user.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.Person;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.user.service.domain.exception.UserDomainException;
import com.multiwarehouse.app.user.service.domain.valuobject.UserAddressId;
import com.multiwarehouse.app.user.service.domain.valuobject.UserProfileId;

import java.util.UUID;

public class UserProfile extends BaseEntity<UserProfileId> {
    private UserId userId;
    private final Person person;
    private final String phoneNumber;
    private final String profilePicture;

    public void initialize() {
        setId(new UserProfileId(UUID.randomUUID()));
    }

    public void validateInitialization() {
        validateInitialId();
        validateFields();
    }

    public void validateInitialId() {
        if (getId() != null) {
            throw  new UserDomainException("UserProfile is not in correct state for initialization");
        }
    }

    public void validate() {
        validateId();
        validateFields();
    }

    public void validateId() {
        if (getId() == null ) throw new UserDomainException("UserProfile Id cannot be null");
    }

    public void validateFields() {
        validateUserId();
        validatePerson();
        validatePhoneNumber();
        validateProfilePicture();
    }

    public void validateUserId() {
        if (getUserId() == null) throw new UserDomainException("UserProfile UserId cannot be null");
    }

    public void validatePerson() {
        if (getPerson() == null) throw new UserDomainException("UserProfile Person cannot be null");
    }

    private void validatePhoneNumber() {
        if (getPhoneNumber() == null || getPhoneNumber().isEmpty()) throw new UserDomainException("UserProfile PhoneNumber cannot be empty");
    }

    private void validateProfilePicture() {
        if (getProfilePicture() == null || getProfilePicture().isEmpty()) throw new UserDomainException("UserProfile ProfilePicture cannot be empty");
    }

    public void setUserId(UserId userId) {
        if (userId == null) return;
        this.userId = userId;
    }

    public UserId getUserId() {
        return userId;
    }

    public Person getPerson() {
        return person;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    private UserProfile(Builder builder) {
        super.setId(builder.id);
        userId = builder.userId;
        person = builder.person;
        phoneNumber = builder.phoneNumber;
        profilePicture = builder.profilePicture;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UserProfileId id;
        private UserId userId;
        private Person person;
        private String phoneNumber;
        private String profilePicture;

        private Builder() {
        }

        public Builder withId(UserProfileId val) {
            id = val;
            return this;
        }

        public Builder withUserId(UserId val) {
            userId = val;
            return this;
        }

        public Builder withPerson(Person val) {
            person = val;
            return this;
        }

        public Builder withPhoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public Builder withProfilePicture(String val) {
            profilePicture = val;
            return this;
        }

        public UserProfile build() {
            return new UserProfile(this);
        }
    }
}
