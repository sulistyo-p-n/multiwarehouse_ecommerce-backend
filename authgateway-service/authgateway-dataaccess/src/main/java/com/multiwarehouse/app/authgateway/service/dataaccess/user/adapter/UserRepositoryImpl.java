package com.multiwarehouse.app.authgateway.service.dataaccess.user.adapter;

import com.multiwarehouse.app.authgateway.service.domain.port.output.repository.UserRepository;
import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.authgateway.service.dataaccess.user.entity.UserEntity;
import com.multiwarehouse.app.authgateway.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.multiwarehouse.app.authgateway.service.dataaccess.user.repository.UserJpaRepository;
import com.multiwarehouse.app.authgateway.service.domain.entity.User;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserDataAccessMapper userDataAccessMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.getValue()).map(userDataAccessMapper::userFromUserEntity);
    }

    @Override
    public User save(User user) {
        return userDataAccessMapper.userFromUserEntity(userJpaRepository
                .save(userDataAccessMapper.userEntityFromUser(user)));
    }

    @Override
    public void hardDelete(User user) {
        userJpaRepository.delete(userDataAccessMapper.userEntityFromUser(user));
    }

    @Override
    public void softDelete(User user) {
        UserEntity userEntity = userDataAccessMapper.userEntityFromUser(user);
        userEntity.setDeletedAt(Instant.now());
        userJpaRepository.save(userEntity);
    }
}
