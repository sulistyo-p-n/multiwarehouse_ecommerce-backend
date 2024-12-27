package com.multiwarehouse.app.user.service.dataaccess.warehouse.repository;

import com.multiwarehouse.app.dataaccess.repository.BaseJpaRepository;
import com.multiwarehouse.app.user.service.dataaccess.warehouse.entity.WarehouseEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WarehouseJpaRepository extends BaseJpaRepository<WarehouseEntity, UUID> {
}
