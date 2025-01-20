package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.jwt.JwtService;
import com.multiwarehouse.app.user.service.domain.dto.login.LoginUserCommand;
import com.multiwarehouse.app.user.service.domain.dto.login.LoginUserResponse;
import com.multiwarehouse.app.user.service.domain.entity.User;
import com.multiwarehouse.app.user.service.domain.exception.UserDomainException;
import com.multiwarehouse.app.user.service.domain.mapper.UserDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
        String token = jwtService.generateToken(user.getId().getValue().toString());
        log.info("Users is selected with token: {}", token);
        return userDataMapper.loginUserResponseFromUser(user, token);
    }

    public void validateUserPassword(User user, String password) {
        log.info("compare: {}", password);
        log.info("compare: {} = {}", user.getPassword(), passwordEncoder.matches(password, user.getPassword()));
        log.info("compare: {} = {}", passwordEncoder.encode(password), passwordEncoder.matches(password, passwordEncoder.encode(password)));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            log.warn("Wrong password ");
            throw new UserDomainException("Wrong password");
        }
    }
}
