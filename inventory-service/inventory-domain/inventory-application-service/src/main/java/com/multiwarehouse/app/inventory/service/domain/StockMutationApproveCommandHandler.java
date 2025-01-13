package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.inventory.service.domain.dto.ApproveStockMutationCommand;
import com.multiwarehouse.app.inventory.service.domain.dto.ApproveStockMutationResponse;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.event.InventoryStockChangedEvent;
import com.multiwarehouse.app.inventory.service.domain.mapper.StockMutationDataMapper;
import com.multiwarehouse.app.inventory.service.domain.ports.output.message.publisher.InventoryStockChangedMessagePublisher;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
public class StockMutationApproveCommandHandler {
    private final InventoryDomainService inventoryDomainService;
    private final StockMutationDataMapper stockMutationDataMapper;
    private final StockMutationHelper stockMutationHelper;
    private final InventoryHelper inventoryHelper;
    private final InventoryStockChangedMessagePublisher inventoryStockChangedMessagePublisher;

    public StockMutationApproveCommandHandler(InventoryDomainService inventoryDomainService, StockMutationDataMapper stockMutationDataMapper, StockMutationHelper stockMutationHelper, InventoryHelper inventoryHelper, InventoryStockChangedMessagePublisher inventoryStockChangedMessagePublisher) {
        this.inventoryDomainService = inventoryDomainService;
        this.stockMutationDataMapper = stockMutationDataMapper;
        this.stockMutationHelper = stockMutationHelper;
        this.inventoryHelper = inventoryHelper;
        this.inventoryStockChangedMessagePublisher = inventoryStockChangedMessagePublisher;
    }

    @Transactional
    public ApproveStockMutationResponse approveStockMutation(ApproveStockMutationCommand approveStockMutationCommand) {
        StockMutation stockMutation = stockMutationHelper.findStockMutationById(new StockMutationId(approveStockMutationCommand.getId()));
        StockMutation stockMutationApproved = inventoryDomainService.approveStockMutation(stockMutation);
        List<InventoryStockChangedEvent> inventoryStockChangedEvents = inventoryDomainService.transferStockMutation(stockMutationApproved, inventoryStockChangedMessagePublisher);
        inventoryStockChangedEvents.forEach(
                inventoryStockChangedEvent -> {
                    inventoryHelper.saveInventory(inventoryStockChangedEvent.getInventory());
                    inventoryStockChangedMessagePublisher.publish(inventoryStockChangedEvent);
                });
        StockMutation stockMutationSaved = stockMutationHelper.saveStockMutation(stockMutationApproved);
        log.info("StockMutation is approved with id: {}", stockMutationSaved.getId().getValue());
        return stockMutationDataMapper.approveStockMutationResponseFromStockMutation(stockMutationSaved);
    }
}
