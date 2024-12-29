package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUserResponse;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.mapper.UserDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class UserGetCommandHandler {
    private final UserDataMapper userDataMapper;
    private final UserHelper userHelper;

    public UserGetCommandHandler(UserDataMapper userDataMapper, UserHelper userHelper) {
        this.userDataMapper = userDataMapper;
        this.userHelper = userHelper;
    }

    @Transactional(readOnly = true)
    public GetUserResponse getUser(GetUserCommand getUserCommand) {
        UserId userId = new UserId(getUserCommand.getId());
        User user = userHelper.findUserById(userId);
        log.info("User is selected with id: {}", user.getId().getValue());
        return userDataMapper.getUserResponseFromUser(user);
    }
}
