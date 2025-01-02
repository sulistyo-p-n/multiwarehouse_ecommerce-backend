package com.multiwarehouse.app.inventory.service.domain.mapper;

import com.multiwarehouse.app.inventory.service.domain.dto.InventoryResponse;
import com.multiwarehouse.app.inventory.service.domain.dto.ProductResponse;
import com.multiwarehouse.app.inventory.service.domain.dto.ProductStockResponse;
import com.multiwarehouse.app.inventory.service.domain.dto.StockJournalResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.entity.ProductStock;
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
                .productStocks(productStockResponsesFromProductStocks(inventory.getProductStocks()))
                .build();
    }

    private List<ProductStockResponse> productStockResponsesFromProductStocks(List<ProductStock> productStocks) {
        return productStocks.stream()
                .map(this::productStockResponseFromProductStock)
                .collect(Collectors.toList());
    }

    private ProductStockResponse productStockResponseFromProductStock(ProductStock productStock) {
        return ProductStockResponse.builder()
                .id(productStock.getId().getValue())
                .product(productDataMapper.productResponseFromProduct(productStock.getProduct()))
                .stockJournals(stockJournalResponsesFromStockJournals(productStock.getStockJournals()))
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
}
