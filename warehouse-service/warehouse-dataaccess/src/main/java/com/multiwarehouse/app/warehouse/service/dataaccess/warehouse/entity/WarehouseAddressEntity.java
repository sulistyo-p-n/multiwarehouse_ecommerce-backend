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
@Table(name = "warehouse_addresses")
@Entity
public class WarehouseAddressEntity {
    @Id
    @OneToOne
    @JoinColumn(name = "id")
    private WarehouseEntity warehouse;

    private String street;
    private String city;
    @Column(name = "postal_code")
    private String postalCode;
    private Float latitude;
    private Float longitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WarehouseAddressEntity that = (WarehouseAddressEntity) o;
        return warehouse.equals(that.warehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(warehouse);
    }
}
