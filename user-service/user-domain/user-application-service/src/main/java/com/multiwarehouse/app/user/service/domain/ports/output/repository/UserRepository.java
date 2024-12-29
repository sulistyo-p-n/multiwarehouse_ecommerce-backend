package com.multiwarehouse.app.user.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.user.service.domain.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository  {
    List<User> findAll();
    List<User> findByCriteria(Boolean withInactive, Boolean withTrashed, UserRole userRole, String search);
    Optional<User> findById(UserId userId);
    User save(User user);
    void hardDelete(User user);
    void softDelete(User user);
}
