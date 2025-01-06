package com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity;

import com.multiwarehouse.app.dataaccess.entity.BaseEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.warehouse.entity.WarehouseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventories")
@Entity
public class InventoryEntity extends BaseEntity {
    @Id
    private UUID id;
    private Boolean active;

    @OneToOne
    @JoinColumn(name = "warehouse_id")
    private WarehouseEntity warehouse;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryStockEntity> stocks;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InventoryEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
