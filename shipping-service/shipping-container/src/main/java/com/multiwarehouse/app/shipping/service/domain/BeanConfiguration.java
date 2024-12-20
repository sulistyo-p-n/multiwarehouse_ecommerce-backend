package com.multiwarehouse.app.shipping.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ShippingDomainService ShippingDomainService() {
        return new ShippingDomainServiceImpl();
    }
}
