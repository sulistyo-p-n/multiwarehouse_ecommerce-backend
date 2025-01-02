package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.inventory.service.domain.dto.RejectStockMutationCommand;
import com.multiwarehouse.app.inventory.service.domain.dto.RejectStockMutationResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.mapper.StockMutationDataMapper;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class StockMutationRejectCommandHandler {
    private final InventoryDomainService inventoryDomainService;
    private final StockMutationDataMapper stockMutationDataMapper;
    private final StockMutationHelper stockMutationHelper;

    public StockMutationRejectCommandHandler(InventoryDomainService inventoryDomainService, StockMutationDataMapper stockMutationDataMapper, StockMutationHelper stockMutationHelper) {
        this.inventoryDomainService = inventoryDomainService;
        this.stockMutationDataMapper = stockMutationDataMapper;
        this.stockMutationHelper = stockMutationHelper;
    }

    @Transactional
    public RejectStockMutationResponse rejectStockMutation(RejectStockMutationCommand rejectStockMutationCommand) {
        StockMutation stockMutation = stockMutationHelper.findStockMutationById(new StockMutationId(rejectStockMutationCommand.getId()));
        StockMutation stockMutationRejected = inventoryDomainService.rejectStockMutation(stockMutation);
        StockMutation stockMutationSaved = stockMutationHelper.saveStockMutation(stockMutationRejected);
        log.info("StockMutation is rejected with id: {}", stockMutationSaved.getId().getValue());
        return stockMutationDataMapper.rejectStockMutationResponseFromStockMutation(stockMutationSaved);
    }
}
