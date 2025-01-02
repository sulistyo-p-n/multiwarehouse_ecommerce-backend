package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.inventory.service.domain.dto.GetStockMutationCommand;
import com.multiwarehouse.app.inventory.service.domain.dto.StockMutationResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.mapper.StockMutationDataMapper;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
public class StockMutationGetCommandHandler {
    private final StockMutationDataMapper stockMutationDataMapper;
    private final StockMutationHelper stockMutationHelper;

    public StockMutationGetCommandHandler(StockMutationDataMapper stockMutationDataMapper, StockMutationHelper stockMutationHelper) {
        this.stockMutationDataMapper = stockMutationDataMapper;
        this.stockMutationHelper = stockMutationHelper;
    }

    @Transactional(readOnly = true)
    public StockMutationResponse getStockMutation(GetStockMutationCommand getStockMutationCommand) {
        StockMutation stockMutation = stockMutationHelper.findStockMutationById(new StockMutationId(getStockMutationCommand.getId()));
        log.info("StockMutation is selected with id: {}", stockMutation.getId().getValue());
        return stockMutationDataMapper.stockMutationResponseFromStockMutation(stockMutation);
    }
}
