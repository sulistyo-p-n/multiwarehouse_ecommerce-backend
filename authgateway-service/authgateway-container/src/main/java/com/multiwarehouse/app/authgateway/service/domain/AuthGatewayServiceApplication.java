package com.multiwarehouse.app.authgateway.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories(basePackages = { "com.multiwarehouse.app.authgateway.service.dataaccess", "com.multiwarehouse.app.dataaccess" })
//@EntityScan(basePackages = { "com.multiwarehouse.app.authgateway.service.dataaccess", "com.multiwarehouse.app.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.multiwarehouse.app")
public class AuthGatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthGatewayServiceApplication.class, args);
    }
}
