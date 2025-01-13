package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryCreatedMessagePublisher;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryDeletedMessagePublisher;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryStockChangedMessagePublisher;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryUpdatedMessagePublisher;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.InventoryRepository;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.ProductRepository;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.StockMutationRepository;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.WarehouseRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "com.multiwarehouse.app")
public class InventoryTestConfiguration {
    @Bean
    public InventoryCreatedMessagePublisher inventoryCreatedMessagePublisher() {
        return Mockito.mock(InventoryCreatedMessagePublisher.class);
    }

    @Bean
    public InventoryDeletedMessagePublisher inventoryDeletedMessagePublisher() {
        return Mockito.mock(InventoryDeletedMessagePublisher.class);
    }

    @Bean
    public InventoryStockChangedMessagePublisher inventoryStockChangedMessagePublisher() {
        return Mockito.mock(InventoryStockChangedMessagePublisher.class);
    }

    @Bean
    public InventoryUpdatedMessagePublisher inventoryUpdatedMessagePublisher() {
        return Mockito.mock(InventoryUpdatedMessagePublisher.class);
    }

    @Bean
    public InventoryRepository inventoryRepository() {
        return Mockito.mock(InventoryRepository.class);
    }

    @Bean
    public ProductRepository productRepository() {
        return Mockito.mock(ProductRepository.class);
    }

    @Bean
    public StockMutationRepository stockMutationRepository() {
        return Mockito.mock(StockMutationRepository.class);
    }

    @Bean
    public WarehouseRepository warehouseRepository() {
        return Mockito.mock(WarehouseRepository.class);
    }

    @Bean
    public InventoryDomainService inventoryDomainService() {
        return new InventoryDomainServiceImpl();
    }
}
