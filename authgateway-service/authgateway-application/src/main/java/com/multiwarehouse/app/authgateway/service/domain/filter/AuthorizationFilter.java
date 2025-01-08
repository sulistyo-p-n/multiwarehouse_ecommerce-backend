package com.multiwarehouse.app.authgateway.service.domain.filter;

import com.multiwarehouse.app.authgateway.service.domain.exception.AuthUnauthorizedException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

@Slf4j
public abstract class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {
    public AuthorizationFilter() {
        super(Config.class);
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
            validateUser(exchange);
            log.info("redirect to {}", exchange.getRequest().getURI().getPath());
            return chain.filter(exchange);
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

    public void validateUser(ServerWebExchange exchange) {
    }
}
