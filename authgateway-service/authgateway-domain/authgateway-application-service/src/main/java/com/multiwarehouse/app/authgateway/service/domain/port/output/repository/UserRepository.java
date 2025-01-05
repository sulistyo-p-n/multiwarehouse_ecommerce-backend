package com.multiwarehouse.app.authgateway.service.domain.port.output.repository;

import com.multiwarehouse.app.authgateway.service.domain.entity.User;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(UserId userId);
    User save(User user);
    void hardDelete(User user);
    void softDelete(User user);
}
