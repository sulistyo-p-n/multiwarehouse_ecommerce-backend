package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.user.service.domain.dto.get.GetUserResponse;
import com.multiwarehouse.app.user.service.domain.dto.get.GetUsersCommand;
import com.multiwarehouse.app.user.service.domain.dto.login.LoginUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.login.LoginUserResponse;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.exception.UserDomainException;
import com.multiwarehouse.app.user.service.domain.exception.UserNotFoundException;
import com.multiwarehouse.app.user.service.domain.mapper.UserDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class UserLoginCommandHandler {
    private final UserDataMapper userDataMapper;
    private final UserHelper userHelper;

    public UserLoginCommandHandler(UserDataMapper userDataMapper, UserHelper userHelper) {
        this.userDataMapper = userDataMapper;
        this.userHelper = userHelper;
    }

    @Transactional(readOnly = true)
    public LoginUserResponse loginUser(LoginUserCommand loginUserCommand) {
        User user = userHelper.findUserByEmail(loginUserCommand.getEmail());
        validateUserPassword(user, loginUserCommand.getPassword());
        log.info("Users is selected with id: {}", user.getId().getValue());
        return userDataMapper.loginUserResponseFromUser(user);
    }

    public void validateUserPassword(User user, String password) {
        if (!user.getPassword().equals(password)) {
            log.warn("Wrong password ");
            throw new UserDomainException("Wrong password");
        }
    }
}
