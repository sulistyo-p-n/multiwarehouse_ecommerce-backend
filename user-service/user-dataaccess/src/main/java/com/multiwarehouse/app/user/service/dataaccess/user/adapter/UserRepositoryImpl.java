package com.multiwarehouse.app.user.service.dataaccess.user.adapter;

import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.domain.valueobject.UserRole;
import com.multiwarehouse.app.user.service.dataaccess.user.entity.UserEntity;
import com.multiwarehouse.app.user.service.dataaccess.user.mapper.UserDataAccessMapper;
import com.multiwarehouse.app.user.service.dataaccess.user.repository.UserJpaRepository;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.ports.output.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository userJpaRepository;
    private final UserDataAccessMapper userDataAccessMapper;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository, UserDataAccessMapper userDataAccessMapper) {
        this.userJpaRepository = userJpaRepository;
        this.userDataAccessMapper = userDataAccessMapper;
    }

    @Override
    public List<User> findAll() {
        return userJpaRepository.findAllNotDeleted().stream().map(userDataAccessMapper::userFromUserEntity).collect(Collectors.toList());
    }

    @Override
    public List<User> findByCriteria(Boolean withInactive, Boolean withTrashed, UserRole userRole, String search) {
        return userJpaRepository.findByCriteria(
                withInactive,
                withTrashed,
                userRole,
                search
        ).stream().map(userDataAccessMapper::userFromUserEntity).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(UserId userId) {
        return userJpaRepository.findById(userId.getValue()).map(userDataAccessMapper::userFromUserEntity);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmail(email).map(userDataAccessMapper::userFromUserEntity);
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
