package com.multiwarehouse.app.product.service.dataaccess.product.repository;

import com.multiwarehouse.app.dataaccess.repository.BaseJpaRepository;
import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductEntity;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductJpaRepository extends BaseJpaRepository<ProductEntity, UUID> {
}
