package com.multiwarehouse.app.warehouse.service.dataaccess.stock.repository;

import com.multiwarehouse.app.warehouse.service.dataaccess.stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, UUID> {

    @Query("SELECT s FROM stocks s WHERE s.warehouseId = ?1")
    List<StockEntity> findByWarehouseId(UUID warehouseId);
}
