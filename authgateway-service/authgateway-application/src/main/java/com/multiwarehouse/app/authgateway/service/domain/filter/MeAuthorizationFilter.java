package com.multiwarehouse.app.authgateway.service.domain.filter;

import com.multiwarehouse.app.authgateway.service.domain.entity.User;
import com.multiwarehouse.app.authgateway.service.domain.exception.AuthUnauthorizedException;
import com.multiwarehouse.app.authgateway.service.domain.port.input.service.AuthApplicationService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.net.URI;

@Slf4j
@Component
public class MeAuthorizationFilter extends AbstractGatewayFilterFactory<MeAuthorizationFilter.Config> {

    private final AuthApplicationService authApplicationService;

    public MeAuthorizationFilter(AuthApplicationService authApplicationService) {
        super(Config.class);
        this.authApplicationService = authApplicationService;
    }

    public final static String BEARER = "Bearer";

    @Getter
    @Setter
    public static class Config {
        private String name;

        public Config(String name) {
            this.name = name;
        }
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            validateAuthorization(exchange);

            String token = getToken(exchange);
            log.info("User token : {}", token);
            User user = authApplicationService.validateUser(token);
            URI redirectUri = URI.create("/users/" + user.getId().getValue());
            exchange.getResponse().setStatusCode(HttpStatus.TEMPORARY_REDIRECT);
            exchange.getResponse().getHeaders().setLocation(redirectUri);

            log.info("redirect to {}", exchange.getRequest().getURI().getPath());
            return exchange.getResponse().setComplete();
        };
    }

    private void validateAuthorization(ServerWebExchange exchange) {
        if (!isAuthorization(exchange)) {
            log.error("Not authorized!");
            throw new AuthUnauthorizedException("Not authorized!");
        }
    }

    private boolean isAuthorization(ServerWebExchange exchange) {
        return exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION);
    }

    public String getToken(ServerWebExchange exchange) {
        String bearerHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        return bearerHeader.replace(BEARER,"").trim();
    }
}
