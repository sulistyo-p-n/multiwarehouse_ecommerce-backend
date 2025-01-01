package com.multiwarehouse.app.inventory.service.dataaccess.inventory.repository;

import com.multiwarehouse.app.dataaccess.repository.BaseJpaRepository;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.InventoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface InventoryJpaRepository extends BaseJpaRepository<InventoryEntity, UUID> {
    @Query("SELECT t FROM #{#entityName} t WHERE t.warehouse.id = :warehouseId")
    Optional<InventoryEntity> findByWarehouseId(@Param("warehouseId") UUID warehouseId);
}
