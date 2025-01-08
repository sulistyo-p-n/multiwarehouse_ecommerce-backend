package com.multiwarehouse.app.authgateway.service.domain;

import com.multiwarehouse.app.authgateway.service.domain.entity.User;
import com.multiwarehouse.app.authgateway.service.domain.exception.AuthUnauthorizedException;
import com.multiwarehouse.app.authgateway.service.domain.port.input.service.AuthApplicationService;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@Validated
public class AuthApplicationServiceImpl implements AuthApplicationService {
    private final UserHelper userHelper;

    public AuthApplicationServiceImpl(UserHelper userHelper) {
        this.userHelper = userHelper;
    }

    @Override
    public User validateUser(String token) {
        return userHelper.findUserByToken(token);
    }

    @Override
    public User validateAdmin(String token) {
        User user = userHelper.findUserByToken(token);
        if (!(user.isSuperAdmin() || user.isWarehouseAdmin())) {
            log.warn("Couldn't find Admin with token: {} ", token);
            throw new AuthUnauthorizedException("Not authorized! with token " + token);
        }
        return user;
    }

    @Override
    public User validateSuperAdmin(String token) {
        User user = userHelper.findUserByToken(token);
        if (!user.isSuperAdmin()) {
            log.warn("Couldn't find SuperAdmin with token: {} ", token);
            throw new AuthUnauthorizedException("Not authorized! with token " + token);
        }
        return user;
    }

    @Override
    public User validateWarehouseAdmin(String token, String warehouseId) {
        User user = userHelper.findUserByToken(token);
        if (!(user.isSuperAdmin() || user.isWarehouseAdmin(new WarehouseId(UUID.fromString(warehouseId))))) {
            log.warn("Couldn't find WarehouseAdmin with token: {} ", token);
            throw new AuthUnauthorizedException("Not authorized! with token " + token);
        }
        return user;
    }
}
