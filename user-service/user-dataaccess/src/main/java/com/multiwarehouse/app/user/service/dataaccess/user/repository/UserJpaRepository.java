package com.multiwarehouse.app.user.service.dataaccess.user.repository;

import com.multiwarehouse.app.dataaccess.repository.BaseJpaRepository;
import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.user.service.dataaccess.user.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserJpaRepository extends BaseJpaRepository<UserEntity, UUID> {
    @Query("SELECT t FROM #{#entityName} t " +
            "WHERE (:withTrashed = TRUE OR t.deletedAt IS NULL) " +
            "AND (:withInactive = TRUE OR t.active = TRUE) " +
            "AND (:userRole IS NULL OR t.role = :userRole) " +
            "AND (COALESCE(:search, '') = '' OR LOWER(t.username) LIKE LOWER(concat('%', concat(:search, '%')))) "
    )
    List<UserEntity> findByCriteria(
            @Param("withInactive") Boolean withInactive,
            @Param("withTrashed") Boolean withTrashed,
            @Param("userRole") UserRole userRole,
            @Param("search") String search
    );

    @Query("SELECT t FROM #{#entityName} t WHERE t.username = :username")
    Optional<UserEntity> findByUsername(@Param("username") String username);

    @Query("SELECT t FROM #{#entityName} t WHERE t.email = :email")
    Optional<UserEntity> findByEmail(@Param("email") String email);
}
