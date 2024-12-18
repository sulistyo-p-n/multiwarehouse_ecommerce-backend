package com.multiwarehouse.app.warehouse.service.dataaccess.user.repository;

import com.multiwarehouse.app.warehouse.service.dataaccess.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, UUID> {
}
