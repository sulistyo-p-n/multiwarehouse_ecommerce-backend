package com.multiwarehouse.app.user.service.dataaccess.user.entity;

import com.multiwarehouse.app.dataaccess.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profiles")
@Entity
public class UserProfileEntity extends BaseEntity {
    @Id
    private UUID id;
    private String firstname;
    private String lastname;
    private Date dateOfBirth;
    private String phoneNumber;
    private String profilePicture;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserProfileEntity that = (UserProfileEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
