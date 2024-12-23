package com.multiwarehouse.app.product.service.dataaccess.product.repository;

import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCategoryJpaRepository extends JpaRepository<ProductCategoryEntity, UUID> {
}
