package com.multiwarehouse.app.user.service.dataaccess.user.entity;

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
public class UserAdminWarehouseEntity {
    @Id
    private UUID id;
    @Column(name = "warehouse_id")
    private UUID warehouseId;

    @OneToOne
    @JoinColumn(name = "user_id")
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
