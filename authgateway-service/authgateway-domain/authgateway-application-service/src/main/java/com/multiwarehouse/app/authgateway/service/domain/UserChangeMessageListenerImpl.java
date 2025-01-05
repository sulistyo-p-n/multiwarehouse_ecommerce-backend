package com.multiwarehouse.app.authgateway.service.domain;

import com.multiwarehouse.app.authgateway.service.domain.entity.User;
import com.multiwarehouse.app.authgateway.service.domain.port.input.message.listener.UserChangeMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class UserChangeMessageListenerImpl implements UserChangeMessageListener {
    private final UserHelper userHelper;

    public UserChangeMessageListenerImpl(UserHelper userHelper) {
        this.userHelper = userHelper;
    }

    @Override
    public void userCreated(User user) {
        userHelper.saveUser(user);
    }

    @Override
    public void userUpdated(User user) {
        userHelper.saveUser(user);
    }

    @Override
    public void userSoftDeleted(User user) {
        userHelper.deleteUser(user, false);
    }

    @Override
    public void userHardDeleted(User user) {
        userHelper.deleteUser(user, true);
    }
}
