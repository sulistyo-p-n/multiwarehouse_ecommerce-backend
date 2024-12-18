package com.multiwarehouse.app.warehouse.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "warehouse-service")
public class WarehouseServiceConfigData {
    private String userCreateTopicName;
    private String userUpdateTopicName;
    private String userDeleteTopicName;
    private String productCreateTopicName;
    private String productUpdateTopicName;
    private String productDeleteTopicName;
    private String stockCreateTopicName;
    private String stockUpdateTopicName;
    private String warehouseCreateTopicName;
    private String warehouseUpdateTopicName;
    private String warehouseDeleteTopicName;
}
