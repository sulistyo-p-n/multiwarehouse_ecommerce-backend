package com.multiwarehouse.app.product.service.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public ProductDomainService productDomainService() {
        return new ProductDomainServiceImpl();
    }

    @Bean
    public ProductCategoryDomainService productCategoryDomainService() {
        return new ProductCategoryDomainServiceImpl();
    }
}
