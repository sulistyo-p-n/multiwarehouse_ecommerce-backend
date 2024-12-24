package com.multiwarehouse.app.product.service.dataaccess.product.entity;

import com.multiwarehouse.app.dataaccess.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
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
    private String code;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_category_id")
    private ProductCategoryEntity category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductImageEntity> images;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
    private ProductStockEntity stock;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
