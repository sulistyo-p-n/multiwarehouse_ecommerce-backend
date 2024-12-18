package com.multiwarehouse.app.warehouse.service.domain.port.output.repository;

import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.warehouse.service.domain.entity.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(UserId userId);
}
