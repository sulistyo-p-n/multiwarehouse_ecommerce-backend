package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.dto.RequestStockMutationCommand;
import com.multiwarehouse.app.inventory.service.domain.dto.RequestStockMutationResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.Inventory;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryNotFoundException;
import com.multiwarehouse.app.inventory.service.domain.mapper.StockMutationDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class StockMutationRequestCommandHandler {
    private final InventoryDomainService inventoryDomainService;
    private final StockMutationDataMapper stockMutationDataMapper;
    private final StockMutationHelper stockMutationHelper;
    private final InventoryHelper inventoryHelper;
    private final ProductHelper productHelper;

    public StockMutationRequestCommandHandler(InventoryDomainService inventoryDomainService, StockMutationDataMapper stockMutationDataMapper, StockMutationHelper stockMutationHelper, InventoryHelper inventoryHelper, ProductHelper productHelper) {
        this.inventoryDomainService = inventoryDomainService;
        this.stockMutationDataMapper = stockMutationDataMapper;
        this.stockMutationHelper = stockMutationHelper;
        this.inventoryHelper = inventoryHelper;
        this.productHelper = productHelper;
    }

    @Transactional
    public RequestStockMutationResponse requestStockMutation(RequestStockMutationCommand requestStockMutationCommand) {
        Inventory sourceInventory = inventoryHelper.findInventoryByWarehouseId(new WarehouseId(requestStockMutationCommand.getSourceWarehouseId()));
        Inventory targetInventory = inventoryHelper.findInventoryByWarehouseId(new WarehouseId(requestStockMutationCommand.getTargetWarehouseId()));
        Product product = productHelper.findProductById(new ProductId(requestStockMutationCommand.getProductId()));
        StockMutation stockMutation = StockMutation.builder()
                .withSourceInventory(sourceInventory)
                .withTargetInventory(targetInventory)
                .withProduct(product)
                .withQuantity(requestStockMutationCommand.getQuantity())
                .build();
        StockMutation stockMutationInitialized = inventoryDomainService.requestStockMutation(stockMutation);
        StockMutation stockMutationSaved = stockMutationHelper.saveStockMutation(stockMutationInitialized);
        log.info("StockMutation is requested with id: {}", stockMutationSaved.getId().getValue());
        return stockMutationDataMapper.requestStockMutationResponseFromStockMutation(stockMutationSaved);
    }
}
