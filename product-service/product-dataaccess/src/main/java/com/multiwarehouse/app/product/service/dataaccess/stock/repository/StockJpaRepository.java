package com.multiwarehouse.app.product.service.dataaccess.stock.repository;

import com.multiwarehouse.app.product.service.dataaccess.stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, UUID> {
}