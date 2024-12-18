package com.multiwarehouse.app.warehouse.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "com.multiwarehouse.app.warehouse.service.dataaccess", "com.multiwarehouse.app.dataaccess" })
@EntityScan(basePackages = { "com.multiwarehouse.app.warehouse.service.dataaccess", "com.multiwarehouse.app.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.multiwarehouse.app")
public class WarehouseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(WarehouseServiceApplication.class, args);
    }
}
