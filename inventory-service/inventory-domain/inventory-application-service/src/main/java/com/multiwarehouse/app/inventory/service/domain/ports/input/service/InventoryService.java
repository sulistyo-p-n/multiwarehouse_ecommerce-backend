package com.multiwarehouse.app.inventory.service.domain.ports.input.service;

import com.multiwarehouse.app.inventory.service.domain.dto.*;

import java.util.List;

public interface InventoryService {
    public InventoryResponse getInventory(GetInventoryCommand getInventoryCommand);
    public AddStockToInventoryResponse addStockToInventory(AddStockToInventoryCommand addStockToInventoryCommand);
    public ReduceStockFromInventoryResponse reduceStockFromInventory(ReduceStockFromInventoryCommand reduceStockFromInventoryCommand);

    public List<StockMutationResponse> getStockMutations(GetStockMutationsCommand getStockMutationsCommand);
    public StockMutationResponse getStockMutation(GetStockMutationCommand getStockMutationCommand);
    public RequestStockMutationResponse requestStockMutation(RequestStockMutationCommand requestStockMutationCommand);
    public RejectStockMutationResponse rejectStockMutation(RejectStockMutationCommand rejectStockMutationCommand);
    public AcceptStockMutationResponse acceptStockMutation(AcceptStockMutationCommand acceptStockMutationCommand);
}
