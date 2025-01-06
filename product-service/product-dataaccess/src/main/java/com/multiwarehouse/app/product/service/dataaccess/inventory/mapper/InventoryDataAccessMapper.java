package com.multiwarehouse.app.product.service.dataaccess.inventory.mapper;

import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.product.service.dataaccess.inventory.entity.InventoryEntity;
import com.multiwarehouse.app.product.service.dataaccess.inventory.entity.InventoryStockEntity;
import com.multiwarehouse.app.product.service.domain.entity.Inventory;
import com.multiwarehouse.app.product.service.domain.entity.InventoryStock;
import com.multiwarehouse.app.product.service.domain.valueobject.InventoryStockId;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryDataAccessMapper {


    public Inventory inventoryFromInventoryEntity(InventoryEntity inventoryEntity) {
        InventoryId inventoryId = new InventoryId(inventoryEntity.getId());
        Inventory inventory = Inventory.builder()
                .withId(inventoryId)
                .withWarehouseId(new WarehouseId(inventoryEntity.getWarehouseId()))
                .withActive(inventoryEntity.getActive())
                .withStocks(productStocksFromProductStockEntities(inventoryEntity.getStocks()))
                .build();
        inventory.getStocks().forEach(inventoryStock -> inventoryStock.setInventoryId(inventoryId));
        return inventory;
    }

    private List<InventoryStock> productStocksFromProductStockEntities(List<InventoryStockEntity> productStockEntities) {
        if (productStockEntities == null) return Collections.emptyList();
        return productStockEntities.stream()
                .map(this::productStockFromProductStockEntity)
                .collect(Collectors.toList());
    }

    private InventoryStock productStockFromProductStockEntity(InventoryStockEntity inventoryStockEntity) {
        return InventoryStock.builder()
                .withId(new InventoryStockId(inventoryStockEntity.getId()))
                .withProductId(new ProductId(inventoryStockEntity.getProductId()))
                .withQuantity(inventoryStockEntity.getQuantity())
                .build();
    }

    public InventoryEntity inventoryEntityFromInventory(Inventory inventory) {
        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .id(inventory.getId().getValue())
                .warehouseId(inventory.getWarehouseId().getValue())
                .active(inventory.isActive())
                .stocks(productStockEntitiesFromProductStocks(inventory.getStocks()))
                .build();
        inventoryEntity.getStocks().forEach(inventoryStockEntity -> inventoryStockEntity.setInventory(inventoryEntity));
        return inventoryEntity;
    }

    private List<InventoryStockEntity> productStockEntitiesFromProductStocks(List<InventoryStock> inventoryStocks) {
        if (inventoryStocks == null) return Collections.emptyList();
        return inventoryStocks.stream()
                .map(this::productStockEntityFromProductStock)
                .collect(Collectors.toList());
    }

    private InventoryStockEntity productStockEntityFromProductStock(InventoryStock inventoryStock) {
        return InventoryStockEntity.builder()
                .id(inventoryStock.getId().getValue())
                .productId(inventoryStock.getProductId().getValue())
                .quantity(inventoryStock.getQuantity())
                .build();
    }
}
