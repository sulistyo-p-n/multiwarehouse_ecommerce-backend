package com.multiwarehouse.app.product.service.messaging.mapper;

import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.kafka.inventory.avro.model.InventoryChangeAvroModel;
import com.multiwarehouse.app.kafka.inventory.avro.model.InventoryStockChangeAvroModel;
import com.multiwarehouse.app.product.service.domain.entity.Inventory;
import com.multiwarehouse.app.product.service.domain.entity.InventoryStock;
import com.multiwarehouse.app.product.service.domain.valueobject.InventoryStockId;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class InventoryMessagingDataMapper {
    public Inventory inventoryFromInventoryChangeAvroModel(InventoryChangeAvroModel inventoryChangeAvroModel) {
        InventoryId inventoryId = new InventoryId(inventoryChangeAvroModel.getInventoryId());
        Inventory inventory = Inventory.builder()
                .withId(inventoryId)
                .withWarehouseId(new WarehouseId(inventoryChangeAvroModel.getWarehouseId()))
                .withActive(inventoryChangeAvroModel.getActive())
                .withStocks(inventoryStocksFromInventoryStockChangeAvroModels(inventoryChangeAvroModel.getStocks()))
                .build();
        inventory.getStocks().forEach(inventoryStock -> inventoryStock.setInventoryId(inventoryId));
        return inventory;
    }

    private List<InventoryStock> inventoryStocksFromInventoryStockChangeAvroModels(List<InventoryStockChangeAvroModel> inventoryStockChangeAvroModels) {
        if (inventoryStockChangeAvroModels == null) return Collections.emptyList();
        return inventoryStockChangeAvroModels.stream().map(this::inventoryStockFromInventoryStockChangeAvroModel).collect(Collectors.toList());
    }

    private InventoryStock inventoryStockFromInventoryStockChangeAvroModel(InventoryStockChangeAvroModel inventoryStockChangeAvroModel) {
        return InventoryStock.builder()
                .withId(new InventoryStockId(inventoryStockChangeAvroModel.getInventoryStockId()))
                .withProductId(new ProductId(inventoryStockChangeAvroModel.getProductId()))
                .withQuantity(inventoryStockChangeAvroModel.getQuantity())
                .build();
    }
}
