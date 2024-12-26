package com.multiwarehouse.app.product.service.dataaccess.product.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_stocks_m_view")
@Entity
public class ProductStockEntity {
    @Id
    private UUID id;
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "id", nullable = false)
    private ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductStockEntity that = (ProductStockEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
