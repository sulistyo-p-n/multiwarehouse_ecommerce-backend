package com.multiwarehouse.app.user.service.domain.mapper;

import com.multiwarehouse.app.domain.valueobject.*;
import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.create.CreateUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.login.LoginUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.register.RegisterUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.register.RegisterUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.update.UpdateUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.update.UpdateUserResponse;
import com.multiwarehouse.app.user.service.domain.entity.UserAdminWarehouse;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.entity.UserAddress;
import com.multiwarehouse.app.user.service.domain.entity.UserProfile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDataMapper {

    private final PasswordEncoder passwordEncoder;

    public UserDataMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User userFromCreateUserCommand(CreateUserCommand createUserCommand) {
        return User.builder()
                .withUsername(createUserCommand.getUsername())
                .withEmail(createUserCommand.getEmail())
                .withPassword(passwordEncoder.encode(createUserCommand.getPassword()))
                .withActive(createUserCommand.getActive())
                .withRole(createUserCommand.getRole())
                .withUserAdminWarehouse(adminEntityFromUserAdminWarehouse(createUserCommand.getAdminWarehouse()))
                .withUserProfile(userProfileEntityFromUserProfile(createUserCommand.getProfile()))
                .withUserAddresses(userAddressEntitiesFromUserAddresses(createUserCommand.getAddresses()))
                .build();
    }

    public CreateUserResponse createUserResponseFromUser(User user) {
        return CreateUserResponse.builder()
                .id(user.getId().getValue())
                .build();
    }

    public User userFromUpdateUserCommand(UpdateUserCommand updateUserCommand) {
        return User.builder()
                .withId(new UserId(updateUserCommand.getId()))
                .withUsername(updateUserCommand.getUsername())
                .withEmail(updateUserCommand.getEmail())
                .withPassword(updateUserCommand.getPassword() == null ? null : passwordEncoder.encode(updateUserCommand.getPassword()))
                .withActive(updateUserCommand.getActive())
                .withRole(updateUserCommand.getRole())
                .withUserAdminWarehouse(adminEntityFromUserAdminWarehouse(updateUserCommand.getAdminWarehouse()))
                .withUserProfile(userProfileEntityFromUserProfile(updateUserCommand.getProfile()))
                .withUserAddresses(userAddressEntitiesFromUserAddresses(updateUserCommand.getAddresses()))
                .build();
    }

    public UpdateUserResponse updateUserResponseFromUser(User user) {
        return UpdateUserResponse.builder()
                .id(user.getId().getValue())
                .build();
    }

    public User userFromRegisterUserCommand(RegisterUserCommand registerUserCommand) {
        return User.builder()
                .withUsername(registerUserCommand.getUsername())
                .withEmail(registerUserCommand.getEmail())
                .withPassword(passwordEncoder.encode(registerUserCommand.getPassword()))
                .withActive(true)
                .withRole(UserRole.CUSTOMER)
                .build();
    }

    public RegisterUserResponse registerUserResponseFromUser(User user) {
        return RegisterUserResponse.builder()
                .id(user.getId().getValue())
                .build();
    }

    private UserAdminWarehouse adminEntityFromUserAdminWarehouse(com.multiwarehouse.app.user.service.domain.dto.UserAdminWarehouse userAdminWarehouse) {
        if (userAdminWarehouse == null) return null;
        return UserAdminWarehouse.builder()
                .withWarehouseId(new WarehouseId(userAdminWarehouse.getWarehouseId()))
                .build();
    }

    private UserProfile userProfileEntityFromUserProfile(com.multiwarehouse.app.user.service.domain.dto.UserProfile userProfile) {
        if (userProfile == null) return null;
        return UserProfile.builder()
                .withPerson(new Person(userProfile.getFirstname(), userProfile.getLastname(), userProfile.getDateOfBirth()))
                .withPhoneNumber(userProfile.getPhoneNumber())
                .withProfilePicture(userProfile.getProfilePicture())
                .build();
    }

    private List<UserAddress> userAddressEntitiesFromUserAddresses(List<com.multiwarehouse.app.user.service.domain.dto.UserAddress> userAddresses) {
        if (userAddresses == null) return Collections.emptyList();
        return userAddresses.stream().map(this::userAddressEntityFromUserAddress).collect(Collectors.toList());
    }

    private UserAddress userAddressEntityFromUserAddress(com.multiwarehouse.app.user.service.domain.dto.UserAddress userAddress) {
        return UserAddress.builder()
                .withAddress(new Address(
                        userAddress.getStreet(),
                        userAddress.getCity(),
                        userAddress.getPostalCode(),
                        userAddress.getLatitude(),
                        userAddress.getLongitude()))
                .build();
    }

    public GetUserResponse getUserResponseFromUser(User user) {
        return GetUserResponse.builder()
                .id(user.getId().getValue())
                .username(user.getUsername())
                .email(user.getEmail())
                .active(user.isActive())
                .role(user.getRole())
                .adminWarehouse(userAdminWarehouseFromUserAdminWarehouseEntity(user.getUserAdminWarehouse()))
                .profile(userProfileFromUserProfileEntity(user.getUserProfile()))
                .addresses(userAddressesFromUserAddressEntities(user.getUserAddresses()))
                .isSoftDeleted(user.isSoftDeleted())
                .build();
    }

    public LoginUserResponse loginUserResponseFromUser(User user, String token) {
        return LoginUserResponse.builder()
                .token(token)
                .id(user.getId().getValue())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .adminWarehouse(userAdminWarehouseFromUserAdminWarehouseEntity(user.getUserAdminWarehouse()))
                .profile(userProfileFromUserProfileEntity(user.getUserProfile()))
                .addresses(userAddressesFromUserAddressEntities(user.getUserAddresses()))
                .build();
    }

    private com.multiwarehouse.app.user.service.domain.dto.UserAdminWarehouse userAdminWarehouseFromUserAdminWarehouseEntity(UserAdminWarehouse userAdminWarehouse) {
        if (userAdminWarehouse == null) return null;
        return com.multiwarehouse.app.user.service.domain.dto.UserAdminWarehouse.builder()
                .warehouseId(userAdminWarehouse.getWarehouseId().getValue())
                .build();
    }

    private com.multiwarehouse.app.user.service.domain.dto.UserProfile userProfileFromUserProfileEntity(UserProfile userProfile) {
        if (userProfile == null) return null;
        return com.multiwarehouse.app.user.service.domain.dto.UserProfile.builder()
                .firstname(userProfile.getPerson().getFirstname())
                .lastname(userProfile.getPerson().getLastname())
                .dateOfBirth(userProfile.getPerson().getDateOfBirth())
                .phoneNumber(userProfile.getPhoneNumber())
                .profilePicture(userProfile.getProfilePicture())
                .build();
    }

    private List<com.multiwarehouse.app.user.service.domain.dto.UserAddress> userAddressesFromUserAddressEntities(List<UserAddress> userAddresses) {
        return userAddresses.stream().map(this::userAddressFromUserAddressEntity).collect(Collectors.toList());
    }

    private com.multiwarehouse.app.user.service.domain.dto.UserAddress userAddressFromUserAddressEntity(UserAddress userAddress) {
        return com.multiwarehouse.app.user.service.domain.dto.UserAddress.builder()
                .street(userAddress.getAddress().getStreet())
                .city(userAddress.getAddress().getCity())
                .postalCode(userAddress.getAddress().getPostalCode())
                .latitude(userAddress.getAddress().getLatitude())
                .longitude(userAddress.getAddress().getLongitude())
                .build();
    }
}
