package com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity;

import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "inventories")
@Entity
public class StockJournalEntity {
    @Id
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_stock_id")
    private ProductStockEntity productStock;

    private int quantity;
    private StockJournalType type;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StockJournalEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
