package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.dto.AddStockToInventoryCommand;
import com.multiwarehouse.app.inventory.service.domain.dto.AddStockToInventoryResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;
import com.multiwarehouse.app.inventory.service.domain.mapper.InventoryDataMapper;
import com.multiwarehouse.app.inventory.service.domain.ports.ouput.message.publisher.InventoryStockChangedMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class InventoryAddStockCommandHandler {
    private final InventoryDomainService inventoryDomainService;
    private final InventoryDataMapper inventoryDataMapper;
    private final InventoryHelper inventoryHelper;
    private final ProductHelper productHelper;
    private final InventoryStockChangedMessagePublisher inventoryStockChangedMessagePublisher;

    public InventoryAddStockCommandHandler(InventoryDomainService inventoryDomainService, InventoryDataMapper inventoryDataMapper, InventoryHelper inventoryHelper, ProductHelper productHelper, InventoryStockChangedMessagePublisher inventoryStockChangedMessagePublisher) {
        this.inventoryDomainService = inventoryDomainService;
        this.inventoryDataMapper = inventoryDataMapper;
        this.inventoryHelper = inventoryHelper;
        this.productHelper = productHelper;
        this.inventoryStockChangedMessagePublisher = inventoryStockChangedMessagePublisher;
    }

    @Transactional
    public AddStockToInventoryResponse addStockToInventory(AddStockToInventoryCommand addStockToInventoryCommand) {
        Inventory inventory = inventoryHelper.findInventoryByWarehouseId(new WarehouseId(addStockToInventoryCommand.getWarehouseId()));
        Product product = productHelper.findProductById(new ProductId(addStockToInventoryCommand.getProductId()));
        InventoryStockChangedEvent inventoryStockChangedEvent = inventoryDomainService.addStock(inventory, product, addStockToInventoryCommand.getQuantity(), inventoryStockChangedMessagePublisher);
        Inventory inventorySaved = inventoryHelper.saveInventory(inventory);
        log.info("Inventory Stock is added with id: {}", inventorySaved.getId().getValue());
        return inventoryDataMapper.addStockToInventoryResponseFromInventory(inventorySaved);
    }
}
