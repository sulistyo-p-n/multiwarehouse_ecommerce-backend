package com.multiwarehouse.app.authgateway.service.domain.port.input.message.listener;

import com.multiwarehouse.app.authgateway.service.domain.entity.User;

public interface UserChangeMessageListener {
    void userCreated(User user);
    void userUpdated(User user);
    void userSoftDeleted(User user);
    void userHardDeleted(User user);
}
