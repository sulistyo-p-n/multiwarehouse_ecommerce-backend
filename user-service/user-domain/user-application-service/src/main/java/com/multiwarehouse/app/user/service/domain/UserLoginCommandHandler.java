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
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserLoginCommandHandler(UserDataMapper userDataMapper, UserHelper userHelper, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userDataMapper = userDataMapper;
        this.userHelper = userHelper;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional(readOnly = true)
    public LoginUserResponse loginUser(LoginUserCommand loginUserCommand) {
        User user = userHelper.findUserByEmail(loginUserCommand.getEmail());
        validateUserPassword(user, loginUserCommand.getPassword());
        log.info("Users is selected with id: {}", user.getId().getValue());
        String token = jwtService.generateToken(user);
        log.info("Users is selected with token: {}", token);
        return userDataMapper.loginUserResponseFromUser(user, token);
    }

    public void validateUserPassword(User user, String password) {
        if (passwordEncoder.matches(password, user.getPassword())) {
            log.warn("Wrong password ");
            throw new UserDomainException("Wrong password");
        }
    }
}
