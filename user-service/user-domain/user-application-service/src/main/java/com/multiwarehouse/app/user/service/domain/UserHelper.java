package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.domain.valueobject.*;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUsersCommand;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.entity.Warehouse;
import com.multiwarehouse.app.user.service.domain.exception.UserDomainException;
import com.multiwarehouse.app.user.service.domain.exception.UserNotFoundException;
import com.multiwarehouse.app.user.service.domain.ports.output.repository.UserRepository;
import com.multiwarehouse.app.user.service.domain.ports.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserHelper {
    private final UserRepository userRepository;
    private final WarehouseRepository warehouseRepository;

    public UserHelper(UserRepository userRepository, WarehouseRepository warehouseRepository) {
        this.userRepository = userRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public List<User> findUsers(GetUsersCommand getUsersCommand) {
        return userRepository.findByCriteria(
                getUsersCommand.getWithInactive(),
                getUsersCommand.getWithTrashed(),
                getUsersCommand.getUserRole(),
                getUsersCommand.getSearch());
    }

    public User findUserById(UserId userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            log.warn("Couldn't find User with id: {} ", userId.getValue());
            throw new UserNotFoundException("Couldn't find User with id: " + userId.getValue());
        }
        return user.get();
    }

    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isEmpty()) {
            log.warn("Couldn't find User with email: {} ", email);
            throw new UserNotFoundException("Couldn't find User with email: " + email);
        }
        return user.get();
    }

    public User persistUser(User user) {
        checkAdminWarehouse(user);
        return saveUser(user);
    }

    private void checkAdminWarehouse(User user) {
        if (user.getRole() != UserRole.WAREHOUSE_ADMIN) return;
        WarehouseId warehouseId = user.getUserAdminWarehouse().getWarehouseId();
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseId);
        if (warehouse.isEmpty()) {
            log.warn("Couldn't find warehouse with id: {} ", warehouseId.getValue());
            throw new UserNotFoundException("Couldn't find warehouse with id: " + warehouseId.getValue());
        }
    }

    private User saveUser(User user) {
        User userResult = userRepository.save(user);
        if (userResult == null) {
            log.error("Couldn't save User!");
            throw new UserDomainException("Cloud not save User!");
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
            throw new UserDomainException("Cloud not delete User!");
        }
        log.info("User is deleted with id : {}", user.getId().getValue());
    }
}
