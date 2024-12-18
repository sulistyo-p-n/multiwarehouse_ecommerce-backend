package com.multiwarehouse.app.user.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public UserDomainService UserDomainService() {
        return new UserDomainServiceImpl();
    }
}
