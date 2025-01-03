package com.multiwarehouse.app.inventory.service.dataaccess.product.entity;

import com.multiwarehouse.app.dataaccess.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "products")
@Entity
public class ProductEntity extends BaseEntity {
    @Id
    private UUID id;
    @Column(name = "product_category_id")
    private UUID categoryId;
    private String code;
    private String name;
    private BigDecimal price;
    private Boolean active;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
