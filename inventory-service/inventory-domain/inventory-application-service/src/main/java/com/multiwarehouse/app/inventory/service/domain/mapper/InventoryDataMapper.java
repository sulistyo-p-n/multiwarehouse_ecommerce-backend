package com.multiwarehouse.app.inventory.service.domain.mapper;

import com.multiwarehouse.app.inventory.service.domain.dto.*;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.InventoryStock;
import com.multiwarehouse.app.inventory.service.domain.entity.StockJournal;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryDataMapper {
    private final ProductDataMapper productDataMapper;

    public InventoryDataMapper(ProductDataMapper productDataMapper) {
        this.productDataMapper = productDataMapper;
    }

    public InventoryResponse inventoryResponseFromInventory(Inventory inventory) {
        return InventoryResponse.builder()
                .id(inventory.getId().getValue())
                .active(inventory.isActive())
                .stocks(inventoryStockResponsesFromInventoryStocks(inventory.getStocks()))
                .softDeleted(inventory.isSoftDeleted())
                .build();
    }

    private List<InventoryStockResponse> inventoryStockResponsesFromInventoryStocks(List<InventoryStock> inventoryStocks) {
        return inventoryStocks.stream()
                .map(this::inventoryStockResponseFromInventoryStock)
                .collect(Collectors.toList());
    }

    private InventoryStockResponse inventoryStockResponseFromInventoryStock(InventoryStock inventoryStock) {
        return InventoryStockResponse.builder()
                .id(inventoryStock.getId().getValue())
                .quantity(inventoryStock.getQuantity())
                .product(productDataMapper.productResponseFromProduct(inventoryStock.getProduct()))
                .journals(stockJournalResponsesFromStockJournals(inventoryStock.getJournals()))
                .build();
    }

    private List<StockJournalResponse> stockJournalResponsesFromStockJournals(List<StockJournal> stockJournals) {
        return stockJournals.stream()
                .map(this::stockJournalResponseFromStockJournal)
                .collect(Collectors.toList());
    }

    private StockJournalResponse stockJournalResponseFromStockJournal(StockJournal stockJournal) {
        return StockJournalResponse.builder()
                .quantity(stockJournal.getQuantity())
                .type(stockJournal.getType())
                .createdAt(stockJournal.getCreatedAt())
                .build();
    }

    public AddStockToInventoryResponse addStockToInventoryResponseFromInventory(Inventory inventory) {
        return AddStockToInventoryResponse.builder()
                .id(inventory.getId().getValue())
                .build();
    }

    public ReduceStockFromInventoryResponse reduceStockFromInventoryResponseFromInventory(Inventory inventory) {
        return ReduceStockFromInventoryResponse.builder()
                .id(inventory.getId().getValue())
                .build();
    }
}
