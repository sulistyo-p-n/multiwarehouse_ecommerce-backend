package com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.entity;

import com.multiwarehouse.app.dataaccess.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouses")
@Entity
public class WarehouseEntity extends BaseEntity {
    @Id
    private UUID id;
    private String code;
    private String name;
    private String description;
    private Boolean active;

    @OneToOne(mappedBy = "warehouse")
    private WarehouseAddressEntity address;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseEntity that = (WarehouseEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
