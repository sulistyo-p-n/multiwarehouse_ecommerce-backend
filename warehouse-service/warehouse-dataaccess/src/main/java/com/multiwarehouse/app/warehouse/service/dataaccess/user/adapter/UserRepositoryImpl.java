package com.multiwarehouse.app.warehouse.service.dataaccess.user.adapter;

import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.warehouse.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.multiwarehouse.app.warehouse.service.dataaccess.user.repository.UserJpaRepository;
import com.multiwarehouse.app.warehouse.service.domain.entity.User;
import com.multiwarehouse.app.warehouse.service.domain.port.output.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository,
                                 UserDataAccessMapper userDataAccessMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.getValue()).map(userDataAccessMapper::userEntityToUser);
    }
}
