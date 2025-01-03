package com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "warehouses")
@Entity
public class WarehouseAddressEntity {
    @Id
    private UUID id;
    private String street;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private Float latitude;
    private Float longitude;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private WarehouseEntity warehouse;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseAddressEntity that = (WarehouseAddressEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
