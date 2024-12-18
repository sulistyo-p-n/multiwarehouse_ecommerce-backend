package com.multiwarehouse.app.warehouse.service.domain.port.input.message.listener.user;

import com.multiwarehouse.app.warehouse.service.domain.dto.message.user.UserResponse;

public interface UserUpdatedMessageListener {

    void UserUpdated(UserResponse userResponse);
}
