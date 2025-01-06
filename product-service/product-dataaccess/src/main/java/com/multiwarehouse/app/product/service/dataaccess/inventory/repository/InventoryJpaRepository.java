package com.multiwarehouse.app.product.service.dataaccess.inventory.repository;

import com.multiwarehouse.app.dataaccess.repository.BaseJpaRepository;
import com.multiwarehouse.app.product.service.dataaccess.inventory.entity.InventoryEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryJpaRepository extends BaseJpaRepository<InventoryEntity, UUID> {
}