package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.dto.GetInventoryCommand;
import com.multiwarehouse.app.inventory.service.domain.dto.InventoryResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.mapper.InventoryDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class InventoryGetCommandHandler {
    private final InventoryDataMapper inventoryDataMapper;
    private final InventoryHelper inventoryHelper;

    public InventoryGetCommandHandler(InventoryDataMapper inventoryDataMapper, InventoryHelper inventoryHelper) {
        this.inventoryDataMapper = inventoryDataMapper;
        this.inventoryHelper = inventoryHelper;
    }

    @Transactional(readOnly = true)
    public InventoryResponse getInventory(GetInventoryCommand getInventoryCommand) {
        Inventory inventory = inventoryHelper.findInventoryByWarehouseId(new WarehouseId(getInventoryCommand.getWarehouseId()));
        log.info("Inventory is selected with id: {}", inventory.getId().getValue());
        return inventoryDataMapper.inventoryResponseFromInventory(inventory);
    }
}
