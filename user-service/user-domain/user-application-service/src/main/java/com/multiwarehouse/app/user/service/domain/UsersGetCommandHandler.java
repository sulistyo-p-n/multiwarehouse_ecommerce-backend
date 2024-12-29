package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.user.service.domain.dto.get.GetUsersCommand;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUsersResponse;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.mapper.UserDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class UsersGetCommandHandler {
    private final UserDataMapper userDataMapper;
    private final UserHelper userHelper;

    public UsersGetCommandHandler(UserDataMapper userDataMapper, UserHelper userHelper) {
        this.userDataMapper = userDataMapper;
        this.userHelper = userHelper;
    }

    @Transactional(readOnly = true)
    public GetUsersResponse getUsers(GetUsersCommand getUserCommand) {
        List<User> users = userHelper.findUsers(getUserCommand);
        log.info("Users is selected with total: {}", users.size());
        return userDataMapper.getUsersResponseFromUsers(users);
    }
}
