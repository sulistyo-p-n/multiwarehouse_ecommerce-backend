package com.multiwarehouse.app.authgateway.service.domain.filter;

import com.multiwarehouse.app.authgateway.service.domain.port.input.service.AuthApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Component
public class AdminAuthorizationFilter extends AuthorizationFilter {

    private final AuthApplicationService authApplicationService;

    public AdminAuthorizationFilter(AuthApplicationService authApplicationService) {
        super();
        this.authApplicationService = authApplicationService;
    }

    @Override
    public void validateUser(ServerWebExchange exchange) {
        String token = getToken(exchange);
        log.info("Admin token : {}", token);
        authApplicationService.validateAdmin(token);
    }
}
