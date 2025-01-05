package com.multiwarehouse.app.product.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "product-service")
public class ProductServiceConfigData {
    private String productChangeTopicName;
    private String inventoryChangeTopicName;
}
