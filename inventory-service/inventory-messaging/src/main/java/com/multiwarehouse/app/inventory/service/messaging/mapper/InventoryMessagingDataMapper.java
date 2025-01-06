package com.multiwarehouse.app.inventory.service.messaging.mapper;

import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.InventoryStock;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryCreatedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryDeletedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryUpdatedEvent;
import com.multiwarehouse.app.kafka.inventory.avro.model.ActionType;
import com.multiwarehouse.app.kafka.inventory.avro.model.InventoryChangeAvroModel;
import com.multiwarehouse.app.kafka.inventory.avro.model.InventoryStockChangeAvroModel;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class InventoryMessagingDataMapper {
    public InventoryChangeAvroModel inventoryChangeAvroModelFromInventoryCreatedEvent(InventoryCreatedEvent inventoryCreatedEvent) {
        InventoryChangeAvroModel inventoryChangeAvroModel = inventoryChangeAvroModelFromInventory(inventoryCreatedEvent.getInventory());
        inventoryChangeAvroModel.setActionType(ActionType.CREATED);
        inventoryChangeAvroModel.setCreatedAt(inventoryCreatedEvent.getCreatedAt().toInstant());
        return inventoryChangeAvroModel;
    }

    public InventoryChangeAvroModel inventoryChangeAvroModelFromInventoryUpdatedEvent(InventoryUpdatedEvent inventoryUpdatedEvent) {
        InventoryChangeAvroModel inventoryChangeAvroModel = inventoryChangeAvroModelFromInventory(inventoryUpdatedEvent.getInventory());
        inventoryChangeAvroModel.setActionType(ActionType.UPDATED);
        inventoryChangeAvroModel.setCreatedAt(inventoryUpdatedEvent.getCreatedAt().toInstant());
        return inventoryChangeAvroModel;
    }

    public InventoryChangeAvroModel inventoryChangeAvroModelFromInventoryDeletedEvent(InventoryDeletedEvent inventoryDeletedEvent) {
        InventoryChangeAvroModel inventoryChangeAvroModel = inventoryChangeAvroModelFromInventory(inventoryDeletedEvent.getInventory());
        inventoryChangeAvroModel.setActionType(inventoryDeletedEvent.isForceDelete() ? ActionType.HARD_DELETED : ActionType.SOFT_DELETED);
        inventoryChangeAvroModel.setCreatedAt(inventoryDeletedEvent.getCreatedAt().toInstant());
        return inventoryChangeAvroModel;
    }

    public InventoryChangeAvroModel inventoryChangeAvroModelFromInventoryStockChangedEvent(InventoryStockChangedEvent inventoryStockChangedEvent) {
        InventoryChangeAvroModel inventoryChangeAvroModel = inventoryChangeAvroModelFromInventory(inventoryStockChangedEvent.getInventory());
        inventoryChangeAvroModel.setActionType(ActionType.UPDATED);
        inventoryChangeAvroModel.setCreatedAt(inventoryStockChangedEvent.getCreatedAt().toInstant());
        return inventoryChangeAvroModel;
    }

    private InventoryChangeAvroModel inventoryChangeAvroModelFromInventory(Inventory inventory) {
        return InventoryChangeAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setInventoryId(inventory.getId().getValue())
                .setWarehouseId(inventory.getWarehouse().getId().getValue())
                .setActive(inventory.isActive())
                .setStocks(inventoryStockChangeAvroModelsFromInventoryStocks(inventory.getStocks()))
                .setActionType(ActionType.DEFAULT)
                .setCreatedAt(Instant.now())
                .build();
    }

    private List<InventoryStockChangeAvroModel> inventoryStockChangeAvroModelsFromInventoryStocks(List<InventoryStock> inventoryStocks) {
        if (inventoryStocks == null) return Collections.emptyList();
        return inventoryStocks.stream().map(this::inventoryStockChangeAvroModelFromInventoryStock).collect(Collectors.toList());
    }

    private InventoryStockChangeAvroModel inventoryStockChangeAvroModelFromInventoryStock(InventoryStock inventoryStock) {
        return InventoryStockChangeAvroModel.newBuilder()
                .setInventoryStockId(inventoryStock.getId().getValue())
                .setProductId(inventoryStock.getProduct().getId().getValue())
                .setQuantity(inventoryStock.getQuantity())
                .build();
    }
}
