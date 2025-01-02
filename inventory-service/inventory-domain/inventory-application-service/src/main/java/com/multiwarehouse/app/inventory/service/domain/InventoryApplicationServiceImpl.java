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
    @Override
    public InventoryResponse getInventory(GetInventoryCommand getInventoryCommand) {
        return null;
    }

    @Override
    public AddStockToInventoryResponse addStockToInventory(AddStockToInventoryCommand addStockToInventoryCommand) {
        return null;
    }

    @Override
    public ReduceStockFromInventoryResponse reduceStockFromInventory(ReduceStockFromInventoryCommand reduceStockFromInventoryCommand) {
        return null;
    }

    @Override
    public List<StockMutationResponse> getStockMutations(GetStockMutationsCommand getStockMutationsCommand) {
        return List.of();
    }

    @Override
    public StockMutationResponse getStockMutation(GetStockMutationCommand getStockMutationCommand) {
        return null;
    }

    @Override
    public RequestStockMutationResponse requestStockMutation(RequestStockMutationCommand requestStockMutationCommand) {
        return null;
    }

    @Override
    public RejectStockMutationResponse rejectStockMutation(RejectStockMutationCommand rejectStockMutationCommand) {
        return null;
    }

    @Override
    public AcceptStockMutationResponse acceptStockMutation(AcceptStockMutationCommand acceptStockMutationCommand) {
        return null;
    }
}
