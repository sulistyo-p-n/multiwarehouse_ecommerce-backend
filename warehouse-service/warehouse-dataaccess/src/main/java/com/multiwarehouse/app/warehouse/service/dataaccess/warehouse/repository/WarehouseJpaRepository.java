package com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.repository;

import com.multiwarehouse.app.dataaccess.repository.BaseJpaRepository;
import com.multiwarehouse.app.warehouse.service.dataaccess.warehouse.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WarehouseJpaRepository extends BaseJpaRepository<WarehouseEntity, UUID> {
    @Query("SELECT t FROM #{#entityName} t " +
            "WHERE (:withTrashed = TRUE OR t.deletedAt IS NULL) " +
            "AND (:withInactive = TRUE OR t.active = TRUE) " +
            "AND (COALESCE(:search, '') = '' OR LOWER(t.name) LIKE LOWER(concat('%', concat(:search, '%')))) "
    )
    List<WarehouseEntity> findByCriteria(
            @Param("withInactive") Boolean withInactive,
            @Param("withTrashed") Boolean withTrashed,
            @Param("search") String search
    );
}
