package com.multiwarehouse.app.inventory.service.domain.mapper;

import com.multiwarehouse.app.inventory.service.domain.dto.ApproveStockMutationResponse;
import com.multiwarehouse.app.inventory.service.domain.dto.RejectStockMutationResponse;
import com.multiwarehouse.app.inventory.service.domain.dto.RequestStockMutationResponse;
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
                .id(stockMutation.getId().getValue())
                .sourceWarehouse(warehouseDataMapper.warehouseResponseFromWarehouse(stockMutation.getSourceInventory().getWarehouse()))
                .targetWarehouse(warehouseDataMapper.warehouseResponseFromWarehouse(stockMutation.getTargetInventory().getWarehouse()))
                .product(productDataMapper.productResponseFromProduct(stockMutation.getProduct()))
                .quantity(stockMutation.getQuantity())
                .status(stockMutation.getStatus())
                .build();
    }

    public RequestStockMutationResponse requestStockMutationResponseFromStockMutation(StockMutation stockMutation) {
        return RequestStockMutationResponse.builder()
                .id(stockMutation.getId().getValue())
                .build();
    }

    public RejectStockMutationResponse rejectStockMutationResponseFromStockMutation(StockMutation stockMutation) {
        return RejectStockMutationResponse.builder()
                .id(stockMutation.getId().getValue())
                .build();
    }

    public ApproveStockMutationResponse approveStockMutationResponseFromStockMutation(StockMutation stockMutation) {
        return ApproveStockMutationResponse.builder()
                .id(stockMutation.getId().getValue())
                .build();
    }
}
