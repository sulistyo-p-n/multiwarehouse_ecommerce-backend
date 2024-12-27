package com.multiwarehouse.app.user.service.dataaccess.user.entity;

import com.multiwarehouse.app.dataaccess.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
@Entity
public class AdminEntity extends BaseEntity {
    @Id
    private UUID id;
    private UUID warehouseId;
    private Boolean active;

    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminEntity that = (AdminEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
