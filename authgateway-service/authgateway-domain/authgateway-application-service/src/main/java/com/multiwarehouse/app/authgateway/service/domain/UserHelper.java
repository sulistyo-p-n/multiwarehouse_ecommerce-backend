package com.multiwarehouse.app.authgateway.service.domain;

import com.multiwarehouse.app.authgateway.service.domain.entity.User;
import com.multiwarehouse.app.authgateway.service.domain.exception.AuthDomainException;
import com.multiwarehouse.app.authgateway.service.domain.exception.AuthNotFoundException;
import com.multiwarehouse.app.authgateway.service.domain.exception.AuthUnauthorizedException;
import com.multiwarehouse.app.authgateway.service.domain.port.output.repository.UserRepository;
import com.multiwarehouse.app.domain.valueobject.UserId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class UserHelper {
    private final UserRepository userRepository;

    public UserHelper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findUserById(UserId userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            log.warn("Couldn't find User with id: {} ", userId.getValue());
            throw new AuthNotFoundException("Couldn't find User with id: " + userId.getValue());
        }
        return user.get();
    }

    public User findUserByToken(String token) {
        Optional<User> user = userRepository.findById(new UserId(UUID.fromString(token)));
        if (user.isEmpty()) {
            log.warn("Couldn't find User with token: {} ", token);
            throw new AuthUnauthorizedException("Not authorized! with token " + token);
        }
        return user.get();
    }

    public User saveUser(User user) {
        User userResult = userRepository.save(user);
        if (userResult == null) {
            log.error("Couldn't save User!");
            throw new AuthDomainException("Cloud not save User!");
        }
        log.info("User is saved with id : {}", userResult.getId().getValue());
        return userResult;
    }

    public void deleteUser(User user, Boolean forceDelete) {
        try {
            if (forceDelete != null && forceDelete) {
                userRepository.hardDelete(user);
            } else {
                userRepository.softDelete(user);
            }
        } catch (Exception e) {
            throw new AuthDomainException("Cloud not delete User!");
        }
        log.info("User is deleted with id : {}", user.getId().getValue());
    }
}
