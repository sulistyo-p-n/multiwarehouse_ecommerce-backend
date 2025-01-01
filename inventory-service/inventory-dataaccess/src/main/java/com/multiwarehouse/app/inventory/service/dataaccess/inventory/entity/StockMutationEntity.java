package com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity;

import com.multiwarehouse.app.dataaccess.entity.BaseEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.product.entity.ProductEntity;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "stock_mutations")
@Entity
public class StockMutationEntity extends BaseEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "source_inventory_id")
    private InventoryEntity sourceInventory;

    @ManyToOne
    @JoinColumn(name = "target_inventory_id")
    private InventoryEntity targetInventory;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private int quantity;
    private StockMutationStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockMutationEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
