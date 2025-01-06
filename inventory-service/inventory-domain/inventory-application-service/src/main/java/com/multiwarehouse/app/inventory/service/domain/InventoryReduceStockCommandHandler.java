package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.dto.ReduceStockFromInventoryCommand;
import com.multiwarehouse.app.inventory.service.domain.dto.ReduceStockFromInventoryResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;
import com.multiwarehouse.app.inventory.service.domain.mapper.InventoryDataMapper;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryStockChangedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class InventoryReduceStockCommandHandler {
    private final InventoryDomainService inventoryDomainService;
    private final InventoryDataMapper inventoryDataMapper;
    private final InventoryHelper inventoryHelper;
    private final ProductHelper productHelper;
    private final InventoryStockChangedMessagePublisher inventoryStockChangedMessagePublisher;

    public InventoryReduceStockCommandHandler(InventoryDomainService inventoryDomainService, InventoryDataMapper inventoryDataMapper, InventoryHelper inventoryHelper, ProductHelper productHelper, InventoryStockChangedMessagePublisher inventoryStockChangedMessagePublisher) {
        this.inventoryDomainService = inventoryDomainService;
        this.inventoryDataMapper = inventoryDataMapper;
        this.inventoryHelper = inventoryHelper;
        this.productHelper = productHelper;
        this.inventoryStockChangedMessagePublisher = inventoryStockChangedMessagePublisher;
    }

    @Transactional
    public ReduceStockFromInventoryResponse reduceStockFromInventory(ReduceStockFromInventoryCommand reduceStockFromInventoryCommand) {
        Inventory inventory = inventoryHelper.findInventoryByWarehouseId(new WarehouseId(reduceStockFromInventoryCommand.getWarehouseId()));
        Product product = productHelper.findProductById(new ProductId(reduceStockFromInventoryCommand.getProductId()));
        InventoryStockChangedEvent inventoryStockChangedEvent = inventoryDomainService.reduceStock(inventory, product, reduceStockFromInventoryCommand.getQuantity(), inventoryStockChangedMessagePublisher);
        Inventory inventorySaved = inventoryHelper.saveInventory(inventory);
        log.info("Inventory Stock is reduced with id: {}", inventorySaved.getId().getValue());
        inventoryStockChangedMessagePublisher.publish(inventoryStockChangedEvent);
        return inventoryDataMapper.reduceStockFromInventoryResponseFromInventory(inventorySaved);
    }
}
