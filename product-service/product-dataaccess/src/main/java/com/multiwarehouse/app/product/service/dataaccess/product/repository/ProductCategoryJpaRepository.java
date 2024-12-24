package com.multiwarehouse.app.product.service.dataaccess.product.repository;

import com.multiwarehouse.app.dataaccess.repository.BaseJpaRepository;
import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductCategoryJpaRepository extends BaseJpaRepository<ProductCategoryEntity, UUID> {
    @Query("SELECT t FROM #{#entityName} t " +
            "WHERE (:withTrashed = TRUE OR t.deletedAt IS NULL) " +
            "AND (:withInactive = TRUE OR t.active = TRUE) "
    )
    List<ProductCategoryEntity> findByCriteria(
            @Param("withInactive") Boolean withInactive,
            @Param("withTrashed") Boolean withTrashed
    );

}