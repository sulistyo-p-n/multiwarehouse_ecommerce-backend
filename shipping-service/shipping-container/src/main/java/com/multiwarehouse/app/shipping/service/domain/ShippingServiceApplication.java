package com.multiwarehouse.app.shipping.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories(basePackages = { "com.multiwarehouse.app.shipping.service.dataaccess", "com.multiwarehouse.app.dataaccess" })
//@EntityScan(basePackages = { "com.multiwarehouse.app.shipping.service.dataaccess", "com.multiwarehouse.app.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.multiwarehouse.app")
public class ShippingServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShippingServiceApplication.class, args);
    }
}
