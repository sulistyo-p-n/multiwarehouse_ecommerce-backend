package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.dto.GetStockMutationsCommand;
import com.multiwarehouse.app.inventory.service.domain.dto.StockMutationResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.mapper.StockMutationDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class StockMutationsGetCommandHandler {
    private final StockMutationDataMapper stockMutationDataMapper;
    private final StockMutationHelper stockMutationHelper;

    public StockMutationsGetCommandHandler(StockMutationDataMapper stockMutationDataMapper, StockMutationHelper stockMutationHelper) {
        this.stockMutationDataMapper = stockMutationDataMapper;
        this.stockMutationHelper = stockMutationHelper;
    }

    @Transactional(readOnly = true)
    public List<StockMutationResponse> getStockMutations(GetStockMutationsCommand getStockMutationsCommand) {
        List<StockMutation> stockMutations = stockMutationHelper.findStockMutationsByWarehouseId(new WarehouseId(getStockMutationsCommand.getWarehouseId()));
        log.info("StockMutations is selected with total: {}", stockMutations.size());
        return stockMutations.stream().map(stockMutationDataMapper::stockMutationResponseFromStockMutation).collect(Collectors.toList());
    }
}
