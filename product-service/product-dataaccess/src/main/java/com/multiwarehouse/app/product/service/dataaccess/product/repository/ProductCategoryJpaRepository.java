package com.multiwarehouse.app.product.service.dataaccess.product.repository;

import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductCategoryJpaRepository extends JpaRepository<ProductCategory, UUID> {
}
