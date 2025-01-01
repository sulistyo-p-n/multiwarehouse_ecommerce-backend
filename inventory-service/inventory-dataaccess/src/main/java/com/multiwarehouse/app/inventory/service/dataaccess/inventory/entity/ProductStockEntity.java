package com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity;

import com.multiwarehouse.app.inventory.service.dataaccess.product.entity.ProductEntity;
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
@Table(name = "product_stocks")
@Entity
public class ProductStockEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private InventoryEntity inventory;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private int quantity;

    @OneToMany(mappedBy = "productStock", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StockJournalEntity> stockJournals;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductStockEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
