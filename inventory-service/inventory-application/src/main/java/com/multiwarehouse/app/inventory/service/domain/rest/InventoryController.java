package com.multiwarehouse.app.inventory.service.domain.rest;

import com.multiwarehouse.app.inventory.service.domain.dto.*;
import com.multiwarehouse.app.inventory.service.domain.ports.input.service.InventoryApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/inventories", produces = "application/vnd.api.v1+json")
public class InventoryController {
    private final InventoryApplicationService inventoryApplicationService;

    public InventoryController(InventoryApplicationService inventoryApplicationService) {
        this.inventoryApplicationService = inventoryApplicationService;
    }

    @GetMapping(path = "by_warehouse/{warehouseId}")
    public ResponseEntity<InventoryResponse> getInventory(@PathVariable("") UUID warehouseId) {
        log.info("Getting inventory by warehouseId: {}", warehouseId);
        GetInventoryCommand getInventoryCommand = GetInventoryCommand.builder().warehouseId(warehouseId).build();
        InventoryResponse inventoryResponse = inventoryApplicationService.getInventory(getInventoryCommand);
        return ResponseEntity.ok(inventoryResponse);
    }

    @PostMapping(path = "by_warehouse/{warehouseId}/add_product_stock")
    public ResponseEntity<AddStockToInventoryResponse> addProductStock(@PathVariable("warehouseId") UUID warehouseId, @RequestBody AddStockToInventoryCommand addStockToInventoryCommand) {
        log.info("Adding ProductStock to Inventory by warehouseId: {}", warehouseId);
        addStockToInventoryCommand.setWarehouseId(warehouseId);
        AddStockToInventoryResponse addStockToInventoryResponse = inventoryApplicationService.addStockToInventory(addStockToInventoryCommand);
        log.info("Added ProductStock to inventory by warehouseId: {}", warehouseId);
        return ResponseEntity.ok(addStockToInventoryResponse);
    }

    @PostMapping(path = "by_warehouse/{warehouseId}/reduce_product_stock")
    public ResponseEntity<ReduceStockFromInventoryResponse> reduceProductStock(@PathVariable("warehouseId") UUID warehouseId, @RequestBody ReduceStockFromInventoryCommand reduceStockFromInventoryCommand) {
        log.info("Reducing ProductStock to Inventory by warehouseId: {}", warehouseId);
        reduceStockFromInventoryCommand.setWarehouseId(warehouseId);
        ReduceStockFromInventoryResponse reduceStockToInventoryResponse = inventoryApplicationService.reduceStockFromInventory(reduceStockFromInventoryCommand);
        log.info("Reduced ProductStock to inventory by warehouseId: {}", warehouseId);
        return ResponseEntity.ok(reduceStockToInventoryResponse);
    }
}
