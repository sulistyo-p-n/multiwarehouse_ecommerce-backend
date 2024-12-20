package com.multiwarehouse.app.cart.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories(basePackages = { "com.multiwarehouse.app.cart.service.dataaccess", "com.multiwarehouse.app.dataaccess" })
//@EntityScan(basePackages = { "com.multiwarehouse.app.cart.service.dataaccess", "com.multiwarehouse.app.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.multiwarehouse.app")
public class CartServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }
}
