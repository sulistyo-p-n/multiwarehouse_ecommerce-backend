package com.multiwarehouse.app.authgateway.service.dataaccess.user.entity;

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
@Table(name = "user_admin_warehouses")
@Entity
public class UserAdminWarehouseEntity extends BaseEntity {
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
        UserAdminWarehouseEntity that = (UserAdminWarehouseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
