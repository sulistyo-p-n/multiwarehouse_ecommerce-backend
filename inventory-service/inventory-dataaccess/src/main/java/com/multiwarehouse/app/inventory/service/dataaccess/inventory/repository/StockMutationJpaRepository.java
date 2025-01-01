package com.multiwarehouse.app.inventory.service.dataaccess.inventory.repository;

import com.multiwarehouse.app.dataaccess.repository.BaseJpaRepository;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.InventoryEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.StockMutationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockMutationJpaRepository extends BaseJpaRepository<StockMutationEntity, UUID> {
    @Query("SELECT t FROM #{#entityName} t WHERE t.sourceInventory.warehouse.id = :warehouseId OR t.targetInventory.warehouse.id = :warehouseId")
    Optional<StockMutationEntity> findByWarehouseId(@Param("warehouseId") UUID warehouseId);
}
