package com.multiwarehouse.app.user.service.domain.dto;

import com.multiwarehouse.app.domain.valueobject.Person;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class UserProfile {
    @NotNull(message = "Firstname {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Firstname {jakarta.validation.constraints.Size.message}")
    private final String firstname;
    @NotNull(message = "Lastname {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "Lastname {jakarta.validation.constraints.Size.message}")
    private final String lastname;
    @NotNull(message = "DateOfBirth {jakarta.validation.constraints.NotNull.message}")
    private final Date dateOfBirth;
    @NotNull(message = "PhoneNumber {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "PhoneNumber {jakarta.validation.constraints.Size.message}")
    private final String phoneNumber;
    @NotNull(message = "ProfilePicture {jakarta.validation.constraints.NotNull.message}")
    @Size(min = 2, max = 50, message = "ProfilePicture {jakarta.validation.constraints.Size.message}")
    private final String profilePicture;
}
