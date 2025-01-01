package com.multiwarehouse.app.inventory.service.dataaccess.product.repository;

import com.multiwarehouse.app.dataaccess.repository.BaseJpaRepository;
import com.multiwarehouse.app.inventory.service.dataaccess.product.entity.ProductEntity;

import java.util.UUID;

public interface ProductJpaRepository extends BaseJpaRepository<ProductEntity, UUID> {
}
