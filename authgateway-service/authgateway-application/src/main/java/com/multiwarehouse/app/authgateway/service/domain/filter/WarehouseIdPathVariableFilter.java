package com.multiwarehouse.app.authgateway.service.domain.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class WarehouseIdPathVariableFilter extends AbstractGatewayFilterFactory<WarehouseIdPathVariableFilter.Config> {
    public WarehouseIdPathVariableFilter() {
        super(Config.class);
    }

    private static final Pattern UUID_PATTERN = Pattern.compile(".*/by_warehouse/(?<id>[0-9a-fA-F\\-]{36}).*");

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
            String path = exchange.getRequest().getURI().getPath();
            log.info("Captured path: {}", path);
            Matcher matcher = UUID_PATTERN.matcher(path);
            if (matcher.matches()) {
                String uuid = matcher.group("id");
                exchange.getRequest().mutate()
                        .header("X-WAREHOUSE-ID", uuid)
                        .build();
                log.info("Captured WarehouseId: {}", uuid);
            }
            return chain.filter(exchange);
        };
    }
}
