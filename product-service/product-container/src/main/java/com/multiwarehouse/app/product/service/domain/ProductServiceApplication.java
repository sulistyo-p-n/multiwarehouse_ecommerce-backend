package com.multiwarehouse.app.product.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories(basePackages = { "com.multiwarehouse.app.product.service.dataaccess", "com.multiwarehouse.app.dataaccess" })
//@EntityScan(basePackages = { "com.multiwarehouse.app.product.service.dataaccess", "com.multiwarehouse.app.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.multiwarehouse.app")
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
