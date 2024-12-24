package com.multiwarehouse.app.dataaccess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseJpaRepository<T, ID> extends JpaRepository<T, ID> {
    @Query("SELECT t FROM #{#entityName} t WHERE t.deletedAt IS NULL")
    List<T> findAllNotDeleted();

    @Query("SELECT t FROM #{#entityName} t WHERE t.id = :id AND t.deletedAt IS NULL")
    Optional<T> findNotDeletedById(@Param("id") ID id);
}