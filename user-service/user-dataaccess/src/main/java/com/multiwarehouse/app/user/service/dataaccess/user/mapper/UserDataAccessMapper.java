package com.multiwarehouse.app.user.service.dataaccess.user.mapper;

import com.multiwarehouse.app.domain.valueobject.Address;
import com.multiwarehouse.app.domain.valueobject.Person;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.user.service.dataaccess.user.entity.UserAddressEntity;
import com.multiwarehouse.app.user.service.dataaccess.user.entity.UserAdminWarehouseEntity;
import com.multiwarehouse.app.user.service.dataaccess.user.entity.UserEntity;
import com.multiwarehouse.app.user.service.dataaccess.user.entity.UserProfileEntity;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.entity.UserAddress;
import com.multiwarehouse.app.user.service.domain.entity.UserAdminWarehouse;
import com.multiwarehouse.app.user.service.domain.entity.UserProfile;
import com.multiwarehouse.app.user.service.domain.valueobject.UserAddressId;
import com.multiwarehouse.app.user.service.domain.valueobject.UserAdminWarehouseId;
import com.multiwarehouse.app.user.service.domain.valueobject.UserProfileId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataAccessMapper {
    public User userFromUserEntity(UserEntity userEntity) {
        UserId userId = new UserId(userEntity.getId());
        User user = User.builder()
                .withId(userId)
                .withUsername(userEntity.getUsername())
                .withEmail(userEntity.getEmail())
                .withPassword(userEntity.getPassword())
                .withRole(userEntity.getRole())
                .withActive(userEntity.getActive())
                .withUserProfile(userProfileFromUserProfileEntity(userEntity.getUserProfile()))
                .withUserAddresses(userAddressesFromUserAddressEntities(userEntity.getUserAddresses()))
                .withUserAdminWarehouse(userAdminWarehouseFromUserAdminWarehouseEntity(userEntity.getUserAdminWarehouse()))
                .withSoftDeleted(userEntity.isSoftDeleted())
                .build();
        if (user.getUserProfile() != null) user.getUserProfile().setUserId(userId);
        if (user.getUserAddresses() != null) user.getUserAddresses().forEach(userAddress -> userAddress.setUserId(userId));
        if (user.getUserAdminWarehouse() != null) user.getUserAdminWarehouse().setUserId(userId);
        return user;
    }

    private UserProfile userProfileFromUserProfileEntity(UserProfileEntity userProfileEntity) {
        if (userProfileEntity == null) return  null;
        return UserProfile.builder()
                .withId(new UserProfileId(userProfileEntity.getId()))
                .withPerson(new Person(userProfileEntity.getFirstname(), userProfileEntity.getLastname(), userProfileEntity.getDateOfBirth()))
                .withPhoneNumber(userProfileEntity.getPhoneNumber())
                .withProfilePicture(userProfileEntity.getProfilePicture())
                .build();
    }

    private List<UserAddress> userAddressesFromUserAddressEntities(List<UserAddressEntity> userAddressEntities) {
        if (userAddressEntities == null) return  null;
        return userAddressEntities.stream().map(this::userAddressFromUserAddressEntity).collect(Collectors.toList());
    }

    private UserAddress userAddressFromUserAddressEntity(UserAddressEntity userAddressEntity) {
        return UserAddress.builder()
                .withId(new UserAddressId(userAddressEntity.getId()))
                .withAddress(new Address(
                        userAddressEntity.getStreet(),
                        userAddressEntity.getCity(),
                        userAddressEntity.getPostalCode(),
                        userAddressEntity.getLatitude(),
                        userAddressEntity.getLongitude()))
                .build();
    }

    private UserAdminWarehouse userAdminWarehouseFromUserAdminWarehouseEntity(UserAdminWarehouseEntity userAdminWarehouseEntity) {
        if (userAdminWarehouseEntity == null) return null;
        return UserAdminWarehouse.builder()
                .withId(new UserAdminWarehouseId(userAdminWarehouseEntity.getId()))
                .withWarehouseId(new WarehouseId(userAdminWarehouseEntity.getWarehouseId()))
                .build();
    }

    public UserEntity userEntityFromUser(User user) {
        UserEntity userEntity = UserEntity.builder()
                .id(user.getId().getValue())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .active(user.isActive())
                .role(user.getRole())
                .userAdminWarehouse(userAdminWarehouseEntityFromUserAdminWarehouse(user.getUserAdminWarehouse()))
                .userProfile(userProfileEntityFromUserProfile(user.getUserProfile()))
                .userAddresses(userAddressEntitiesFromUserAddresses(user.getUserAddresses()))
                .build();
        if (userEntity.getUserAdminWarehouse() != null) userEntity.getUserAdminWarehouse().setUser(userEntity);
        if (userEntity.getUserProfile() != null) userEntity.getUserProfile().setUser(userEntity);
        if (userEntity.getUserAddresses() != null) userEntity.getUserAddresses().forEach(userAddressEntity -> userAddressEntity.setUser(userEntity));
        return userEntity;
    }

    private UserAdminWarehouseEntity userAdminWarehouseEntityFromUserAdminWarehouse(UserAdminWarehouse userAdminWarehouse) {
        if (userAdminWarehouse == null) return null;
        return UserAdminWarehouseEntity.builder()
                .id(userAdminWarehouse.getId().getValue())
                .warehouseId(userAdminWarehouse.getWarehouseId().getValue())
                .build();
    }

    private UserProfileEntity userProfileEntityFromUserProfile(UserProfile userProfile) {
        if (userProfile == null) return null;
        return UserProfileEntity.builder()
                .id(userProfile.getId().getValue())
                .firstname(userProfile.getPerson().getFirstname())
                .lastname(userProfile.getPerson().getLastname())
                .dateOfBirth(userProfile.getPerson().getDateOfBirth())
                .phoneNumber(userProfile.getPhoneNumber())
                .profilePicture(userProfile.getProfilePicture())
                .build();
    }

    private List<UserAddressEntity> userAddressEntitiesFromUserAddresses(List<UserAddress> userAddresses) {
        if (userAddresses == null) return  null;
        return userAddresses.stream().map(this::userAddressEntityFromUserAddress).collect(Collectors.toList());
    }

    private UserAddressEntity userAddressEntityFromUserAddress(UserAddress userAddress) {
        return UserAddressEntity.builder()
                .id(userAddress.getId().getValue())
                .street(userAddress.getAddress().getStreet())
                .city(userAddress.getAddress().getCity())
                .postalCode(userAddress.getAddress().getPostalCode())
                .latitude(userAddress.getAddress().getLatitude())
                .longitude(userAddress.getAddress().getLongitude())
                .build();
    }
}