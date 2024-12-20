package com.multiwarehouse.app.order.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories(basePackages = { "com.multiwarehouse.app.order.service.dataaccess", "com.multiwarehouse.app.dataaccess" })
//@EntityScan(basePackages = { "com.multiwarehouse.app.order.service.dataaccess", "com.multiwarehouse.app.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.multiwarehouse.app")
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
