package com.multiwarehouse.app.inventory.service.dataaccess.inventory.mapper;

import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.StockMutationEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.product.mapper.ProductDataAccessMapper;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationId;
import org.springframework.stereotype.Component;

@Component
public class StockMutationDataAccessMapper {
    private final InventoryDataAccessMapper inventoryDataAccessMapper;
    private final ProductDataAccessMapper productDataAccessMapper;

    public StockMutationDataAccessMapper(InventoryDataAccessMapper inventoryDataAccessMapper, ProductDataAccessMapper productDataAccessMapper) {
        this.inventoryDataAccessMapper = inventoryDataAccessMapper;
        this.productDataAccessMapper = productDataAccessMapper;
    }

    public StockMutation stockMutationFromStockMutationEntity(StockMutationEntity stockMutationEntity) {
        return StockMutation.builder()
                .withId(new StockMutationId(stockMutationEntity.getId()))
                .withSourceInventory(inventoryDataAccessMapper.inventoryFromInventoryEntity(stockMutationEntity.getSourceInventory()))
                .withTargetInventory(inventoryDataAccessMapper.inventoryFromInventoryEntity(stockMutationEntity.getTargetInventory()))
                .withProduct(productDataAccessMapper.productFromProductEntity(stockMutationEntity.getProduct()))
                .withQuantity(stockMutationEntity.getQuantity())
                .withStatus(stockMutationEntity.getStatus())
                .build();
    }

    public StockMutationEntity stockMutationEntityFromStockMutation(StockMutation stockMutation) {
        return StockMutationEntity.builder()
                .id(stockMutation.getId().getValue())
                .sourceInventory(inventoryDataAccessMapper.inventoryEntityFromInventory(stockMutation.getSourceInventory()))
                .targetInventory(inventoryDataAccessMapper.inventoryEntityFromInventory(stockMutation.getTargetInventory()))
                .product(productDataAccessMapper.productEntityFromProduct(stockMutation.getProduct()))
                .quantity(stockMutation.getQuantity())
                .status(stockMutation.getStatus())
                .build();
    }
}
