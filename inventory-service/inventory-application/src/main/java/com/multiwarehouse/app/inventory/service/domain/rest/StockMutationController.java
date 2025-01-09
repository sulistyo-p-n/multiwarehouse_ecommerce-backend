package com.multiwarehouse.app.inventory.service.domain.rest;

import com.multiwarehouse.app.inventory.service.domain.dto.*;
import com.multiwarehouse.app.inventory.service.domain.ports.input.service.InventoryApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/stock_mutations", produces = "application/vnd.api.v1+json")
public class StockMutationController {
    private final InventoryApplicationService inventoryApplicationService;

    public StockMutationController(InventoryApplicationService inventoryApplicationService) {
        this.inventoryApplicationService = inventoryApplicationService;
    }

    @GetMapping(path = "by_warehouse/{warehouseId}")
    public ResponseEntity<List<StockMutationResponse>> getStockMutations(@PathVariable("warehouseId") UUID warehouseId) {
        log.info("Getting StockMutations by warehouseId: {}", warehouseId);
        GetStockMutationsCommand getStockMutationsCommand = GetStockMutationsCommand.builder().warehouseId(warehouseId).build();
        List<StockMutationResponse> stockMutationResponses = inventoryApplicationService.getStockMutations(getStockMutationsCommand);
        return ResponseEntity.ok(stockMutationResponses);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<StockMutationResponse> getStockMutation(@PathVariable("id") UUID id) {
        log.info("Getting StockMutation by id: {}", id);
        GetStockMutationCommand getStockMutationCommand = GetStockMutationCommand.builder().id(id).build();
        StockMutationResponse stockMutationResponse = inventoryApplicationService.getStockMutation(getStockMutationCommand);
        return ResponseEntity.ok(stockMutationResponse);
    }

    @PostMapping(path = "by_warehouse/{warehouseId}")
    public ResponseEntity<RequestStockMutationResponse> requestStockMutation(@PathVariable("warehouseId") UUID warehouseId, @RequestBody RequestStockMutationCommand requestStockMutationCommand) {
        log.info("Requesting StockMutation by warehouseId: {}", warehouseId);
        requestStockMutationCommand.setTargetWarehouseId(warehouseId);
        RequestStockMutationResponse requestStockMutationResponse = inventoryApplicationService.requestStockMutation(requestStockMutationCommand);
        log.info("Requested StockMutation by warehouseId: {}", warehouseId);
        return ResponseEntity.ok(requestStockMutationResponse);
    }

    @PutMapping(path = "{id}/approve")
    public ResponseEntity<ApproveStockMutationResponse> approveStockMutation(@PathVariable("id") UUID id) {
        log.info("Accepting StockMutation by id: {}", id);
        ApproveStockMutationCommand approveStockMutationCommand = ApproveStockMutationCommand.builder().id(id).build();
        ApproveStockMutationResponse approveStockMutationResponse = inventoryApplicationService.approveStockMutation(approveStockMutationCommand);
        log.info("Accepted StockMutation by id: {}", id);
        return ResponseEntity.ok(approveStockMutationResponse);
    }

    @PutMapping(path = "{id}/reject")
    public ResponseEntity<RejectStockMutationResponse> rejectStockMutation(@PathVariable("id") UUID id) {
        log.info("Rejecting StockMutation by id: {}", id);
        RejectStockMutationCommand rejectStockMutationCommand = RejectStockMutationCommand.builder().id(id).build();
        RejectStockMutationResponse rejectStockMutationResponse = inventoryApplicationService.rejectStockMutation(rejectStockMutationCommand);
        log.info("Rejected StockMutation by id: {}", id);
        return ResponseEntity.ok(rejectStockMutationResponse);
    }
}
