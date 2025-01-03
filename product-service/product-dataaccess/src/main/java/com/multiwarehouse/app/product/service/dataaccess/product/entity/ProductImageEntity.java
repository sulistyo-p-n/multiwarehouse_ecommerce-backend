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
@Table(name = "product_images")
@Entity
public class ProductImageEntity {
    @Id
    private UUID id;
    private String name;
    private String description;
    private String path;
    private Boolean front;
    private Boolean active;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductImageEntity that = (ProductImageEntity) o;
        return id.equals(that.id) && product.equals(that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, product);
    }
}