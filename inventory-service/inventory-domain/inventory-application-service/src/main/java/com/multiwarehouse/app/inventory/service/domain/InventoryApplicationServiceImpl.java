package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.inventory.service.domain.dto.*;
import com.multiwarehouse.app.inventory.service.domain.ports.input.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Slf4j
@Service
@Validated
public class InventoryApplicationServiceImpl implements InventoryService {
    private final InventoryGetCommandHandler inventoryGetCommandHandler;
    private final InventoryAddStockCommandHandler inventoryAddStockCommandHandler;
    private final InventoryReduceStockCommandHandler inventoryReduceStockCommandHandler;
    private final StockMutationsGetCommandHandler stockMutationsGetCommandHandler;
    private final StockMutationGetCommandHandler stockMutationGetCommandHandler;
    private final StockMutationRequestCommandHandler stockMutationRequestCommandHandler;
    private final StockMutationRejectCommandHandler stockMutationRejectCommandHandler;
    private final StockMutationApproveCommandHandler stockMutationApproveCommandHandler;

    public InventoryApplicationServiceImpl(InventoryGetCommandHandler inventoryGetCommandHandler, InventoryAddStockCommandHandler inventoryAddStockCommandHandler, InventoryReduceStockCommandHandler inventoryReduceStockCommandHandler, StockMutationsGetCommandHandler stockMutationsGetCommandHandler, StockMutationGetCommandHandler stockMutationGetCommandHandler, StockMutationRequestCommandHandler stockMutationRequestCommandHandler, StockMutationRejectCommandHandler stockMutationRejectCommandHandler, StockMutationApproveCommandHandler stockMutationApproveCommandHandler) {
        this.inventoryGetCommandHandler = inventoryGetCommandHandler;
        this.inventoryAddStockCommandHandler = inventoryAddStockCommandHandler;
        this.inventoryReduceStockCommandHandler = inventoryReduceStockCommandHandler;
        this.stockMutationsGetCommandHandler = stockMutationsGetCommandHandler;
        this.stockMutationGetCommandHandler = stockMutationGetCommandHandler;
        this.stockMutationRequestCommandHandler = stockMutationRequestCommandHandler;
        this.stockMutationRejectCommandHandler = stockMutationRejectCommandHandler;
        this.stockMutationApproveCommandHandler = stockMutationApproveCommandHandler;
    }

    @Override
    public InventoryResponse getInventory(GetInventoryCommand getInventoryCommand) {
        return inventoryGetCommandHandler.getInventory(getInventoryCommand);
    }

    @Override
    public AddStockToInventoryResponse addStockToInventory(AddStockToInventoryCommand addStockToInventoryCommand) {
        return inventoryAddStockCommandHandler.addStockToInventory(addStockToInventoryCommand);
    }

    @Override
    public ReduceStockFromInventoryResponse reduceStockFromInventory(ReduceStockFromInventoryCommand reduceStockFromInventoryCommand) {
        return inventoryReduceStockCommandHandler.reduceStockFromInventory(reduceStockFromInventoryCommand);
    }

    @Override
    public List<StockMutationResponse> getStockMutations(GetStockMutationsCommand getStockMutationsCommand) {
        return stockMutationsGetCommandHandler.getStockMutations(getStockMutationsCommand);
    }

    @Override
    public StockMutationResponse getStockMutation(GetStockMutationCommand getStockMutationCommand) {
        return stockMutationGetCommandHandler.getStockMutation(getStockMutationCommand);
    }

    @Override
    public RequestStockMutationResponse requestStockMutation(RequestStockMutationCommand requestStockMutationCommand) {
        return stockMutationRequestCommandHandler.requestStockMutation(requestStockMutationCommand);
    }

    @Override
    public RejectStockMutationResponse rejectStockMutation(RejectStockMutationCommand rejectStockMutationCommand) {
        return stockMutationRejectCommandHandler.rejectStockMutation(rejectStockMutationCommand);
    }

    @Override
    public ApproveStockMutationResponse acceptStockMutation(ApproveStockMutationCommand approveStockMutationCommand) {
        return stockMutationApproveCommandHandler.approveStockMutation(approveStockMutationCommand);
    }
}
