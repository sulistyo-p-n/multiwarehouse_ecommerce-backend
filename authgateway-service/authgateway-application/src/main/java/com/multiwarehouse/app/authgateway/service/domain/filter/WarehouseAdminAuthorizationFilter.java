package com.multiwarehouse.app.authgateway.service.domain.filter;

import com.multiwarehouse.app.authgateway.service.domain.port.input.service.AuthApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
@Component
public class WarehouseAdminAuthorizationFilter extends AuthorizationFilter {

    private final AuthApplicationService authApplicationService;

    public WarehouseAdminAuthorizationFilter(AuthApplicationService authApplicationService) {
        super();
        this.authApplicationService = authApplicationService;
    }

    @Override
    public void validateUser(ServerWebExchange exchange) {
        String token = getToken(exchange);
        log.info("WarehouseAdmin token : {}", token);
        authApplicationService.validateWarehouseAdmin(token, "");
    }
}
