package com.multiwarehouse.app.inventory.service.domain.mapper;

import com.multiwarehouse.app.inventory.service.domain.dto.StockMutationResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import org.springframework.stereotype.Component;

@Component
public class StockMutationDataMapper {
    private final ProductDataMapper productDataMapper;
    private final WarehouseDataMapper warehouseDataMapper;

    public StockMutationDataMapper(ProductDataMapper productDataMapper, WarehouseDataMapper warehouseDataMapper) {
        this.productDataMapper = productDataMapper;
        this.warehouseDataMapper = warehouseDataMapper;
    }

    public StockMutationResponse stockMutationResponseFromStockMutation(StockMutation stockMutation) {
        return StockMutationResponse.builder()
                .sourceWarehouse(warehouseDataMapper.warehouseResponseFromWarehouse(stockMutation.getSourceInventory().getWarehouse()))
                .targetWarehouse(warehouseDataMapper.warehouseResponseFromWarehouse(stockMutation.getTargetInventory().getWarehouse()))
                .product(productDataMapper.productResponseFromProduct(stockMutation.getProduct()))
                .quantity(stockMutation.getQuantity())
                .status(stockMutation.getStatus())
                .build();
    }
}
