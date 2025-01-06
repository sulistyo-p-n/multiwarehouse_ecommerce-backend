package com.multiwarehouse.app.inventory.service.dataaccess.inventory.mapper;

import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.InventoryEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.InventoryStockEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.StockJournalEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.product.mapper.ProductDataAccessMapper;
import com.multiwarehouse.app.inventory.service.dataaccess.warehouse.mapper.WarehouseDataAccessMapper;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.InventoryStock;
import com.multiwarehouse.app.inventory.service.domain.entity.StockJournal;
import com.multiwarehouse.app.inventory.service.domain.valueobject.InventoryStockId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryDataAccessMapper {
    private final WarehouseDataAccessMapper warehouseDataAccessMapper;
    private final ProductDataAccessMapper productDataAccessMapper;

    public InventoryDataAccessMapper(WarehouseDataAccessMapper warehouseDataAccessMapper, ProductDataAccessMapper productDataAccessMapper) {
        this.warehouseDataAccessMapper = warehouseDataAccessMapper;
        this.productDataAccessMapper = productDataAccessMapper;
    }

    public Inventory inventoryFromInventoryEntity(InventoryEntity inventoryEntity) {
        InventoryId inventoryId = new InventoryId(inventoryEntity.getId());
        Inventory inventory = Inventory.builder()
                .withId(inventoryId)
                .withActive(inventoryEntity.getActive())
                .withWarehouse(warehouseDataAccessMapper.warehouseFromWarehouseEntity(inventoryEntity.getWarehouse()))
                .withStocks(productStocksFromProductStockEntities(inventoryEntity.getStocks()))
                .build();
        inventory.getStocks().forEach(inventoryStock -> inventoryStock.setInventoryId(inventoryId));
        return inventory;
    }

    private List<InventoryStock> productStocksFromProductStockEntities(List<InventoryStockEntity> productStockEntities) {
        return productStockEntities.stream()
                .map(this::productStockFromProductStockEntity)
                .collect(Collectors.toList());
    }

    private InventoryStock productStockFromProductStockEntity(InventoryStockEntity inventoryStockEntity) {
        InventoryStockId inventoryStockId = new InventoryStockId(inventoryStockEntity.getId());
        InventoryStock inventoryStock = InventoryStock.builder()
                .withId(inventoryStockId)
                .withProduct(productDataAccessMapper.productFromProductEntity(inventoryStockEntity.getProduct()))
                .withQuantity(inventoryStockEntity.getQuantity())
                .withJournals(stockJournalsFromStockJournalEntities(inventoryStockEntity.getJournals()))
                .build();
        inventoryStock.getJournals().forEach(stockJournal -> stockJournal.setInventoryStockId(inventoryStockId));
        return inventoryStock;
    }

    private List<StockJournal> stockJournalsFromStockJournalEntities(List<StockJournalEntity> stockJournalEntities) {
        return stockJournalEntities.stream()
                .map(this::stockJournalFromStockJournalEntity)
                .collect(Collectors.toList());
    }

    private StockJournal stockJournalFromStockJournalEntity(StockJournalEntity stockJournalEntity) {
        return StockJournal.builder()
                .withId(new StockJournalId(stockJournalEntity.getId()))
                .withQuantity(stockJournalEntity.getQuantity())
                .withType(stockJournalEntity.getType())
                .withCreatedAt(stockJournalEntity.getCreatedAt())
                .build();
    }

    public InventoryEntity inventoryEntityFromInventory(Inventory inventory) {
        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .id(inventory.getId().getValue())
                .active(inventory.isActive())
                .warehouse(warehouseDataAccessMapper.warehouseEntityFromWarehouse(inventory.getWarehouse()))
                .stocks(productStockEntitiesFromProductStocks(inventory.getStocks()))
                .build();
        inventoryEntity.getStocks().forEach(inventoryStockEntity -> inventoryStockEntity.setInventory(inventoryEntity));
        return inventoryEntity;
    }

    private List<InventoryStockEntity> productStockEntitiesFromProductStocks(List<InventoryStock> inventoryStocks) {
        return inventoryStocks.stream()
                .map(this::productStockEntityFromProductStock)
                .collect(Collectors.toList());
    }

    private InventoryStockEntity productStockEntityFromProductStock(InventoryStock inventoryStock) {
        InventoryStockEntity inventoryStockEntity = InventoryStockEntity.builder()
                .id(inventoryStock.getId().getValue())
                .product(productDataAccessMapper.productEntityFromProduct(inventoryStock.getProduct()))
                .quantity(inventoryStock.getQuantity())
                .journals(stockJournalEntitiesFromStockJournals(inventoryStock.getJournals()))
                .build();
        inventoryStockEntity.getJournals().forEach(stockJournalEntity -> stockJournalEntity.setStock(inventoryStockEntity));
        return inventoryStockEntity;
    }

    private List<StockJournalEntity> stockJournalEntitiesFromStockJournals(List<StockJournal> stockJournals) {
        return stockJournals.stream()
                .map(this::stockJournalEntityFromStockJournal)
                .collect(Collectors.toList());
    }

    private StockJournalEntity stockJournalEntityFromStockJournal(StockJournal stockJournal) {
        return StockJournalEntity.builder()
                .id(stockJournal.getId().getValue())
                .quantity(stockJournal.getQuantity())
                .type(stockJournal.getType())
                .build();
    }

}
