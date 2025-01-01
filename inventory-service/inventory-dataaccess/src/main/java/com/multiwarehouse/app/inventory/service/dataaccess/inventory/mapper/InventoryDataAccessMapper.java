package com.multiwarehouse.app.inventory.service.dataaccess.inventory.mapper;

import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.InventoryEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.ProductStockEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.StockJournalEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.product.mapper.ProductDataAccessMapper;
import com.multiwarehouse.app.inventory.service.dataaccess.warehouse.mapper.WarehouseDataAccessMapper;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.ProductStock;
import com.multiwarehouse.app.inventory.service.domain.entity.StockJournal;
import com.multiwarehouse.app.inventory.service.domain.valueobject.ProductStockId;
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
                .withWarehouse(warehouseDataAccessMapper.warehouseFromWarehouseEntity(inventoryEntity.getWarehouse()))
                .withProductStocks(productStocksFromProductStockEntities(inventoryEntity.getProductStocks()))
                .build();
        inventory.getProductStocks().forEach(productStock -> productStock.setInventoryId(inventoryId));
        return inventory;
    }

    private List<ProductStock> productStocksFromProductStockEntities(List<ProductStockEntity> productStockEntities) {
        return productStockEntities.stream()
                .map(this::productStockFromProductStockEntity)
                .collect(Collectors.toList());
    }

    private ProductStock productStockFromProductStockEntity(ProductStockEntity productStockEntity) {
        ProductStockId productStockId = new ProductStockId(productStockEntity.getId());
        ProductStock productStock = ProductStock.builder()
                .withId(productStockId)
                .withProduct(productDataAccessMapper.productFromProductEntity(productStockEntity.getProduct()))
                .withQuantity(productStockEntity.getQuantity())
                .withStockJournals(stockJournalsFromStockJournalEntities(productStockEntity.getStockJournals()))
                .build();
        productStock.getStockJournals().forEach(stockJournal -> stockJournal.setProductStockId(productStockId));
        return productStock;
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
                .build();
    }

    public InventoryEntity inventoryEntityFromInventory(Inventory inventory) {
        InventoryEntity inventoryEntity = InventoryEntity.builder()
                .id(inventory.getId().getValue())
                .warehouse(warehouseDataAccessMapper.warehouseEntityFromWarehouse(inventory.getWarehouse()))
                .productStocks(productStockEntitiesFromProductStocks(inventory.getProductStocks()))
                .build();
        inventoryEntity.getProductStocks().forEach(productStockEntity -> productStockEntity.setInventory(inventoryEntity));
        return inventoryEntity;
    }

    private List<ProductStockEntity> productStockEntitiesFromProductStocks(List<ProductStock> productStocks) {
        return productStocks.stream()
                .map(this::productStockEntityFromProductStock)
                .collect(Collectors.toList());
    }

    private ProductStockEntity productStockEntityFromProductStock(ProductStock productStock) {
        ProductStockEntity productStockEntity = ProductStockEntity.builder()
                .id(productStock.getId().getValue())
                .product(productDataAccessMapper.productEntityFromProduct(productStock.getProduct()))
                .quantity(productStock.getQuantity())
                .stockJournals(stockJournalEntitiesFromStockJournals(productStock.getStockJournals()))
                .build();
        productStockEntity.getStockJournals().forEach(stockJournalEntity -> stockJournalEntity.setProductStock(productStockEntity));
        return productStockEntity;
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
