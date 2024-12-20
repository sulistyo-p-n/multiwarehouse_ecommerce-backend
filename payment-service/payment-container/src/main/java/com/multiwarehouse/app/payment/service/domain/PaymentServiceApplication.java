package com.multiwarehouse.app.payment.service.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableJpaRepositories(basePackages = { "com.multiwarehouse.app.payment.service.dataaccess", "com.multiwarehouse.app.dataaccess" })
//@EntityScan(basePackages = { "com.multiwarehouse.app.payment.service.dataaccess", "com.multiwarehouse.app.dataaccess"})
@SpringBootApplication(scanBasePackages = "com.multiwarehouse.app")
public class PaymentServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication.class, args);
    }
}
