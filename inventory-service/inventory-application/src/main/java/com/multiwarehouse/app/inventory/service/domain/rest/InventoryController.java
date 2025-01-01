package com.multiwarehouse.app.inventory.service.domain.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/inventories", produces = "application/vnd.api.v1+json")
public class InventoryController {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("get Inventory");
    }

    @GetMapping(path = "by_warehouse/{warehouseId}")
    public ResponseEntity<String> getInventory(@PathVariable("warehouseId") UUID id) {
        log.info("Getting inventory by warehouse_id: {}", id);
        return ResponseEntity.ok("get Inventory");
    }

    @PostMapping(path = "by_warehouse/{warehouseId}/add_product_stock")
    public ResponseEntity<String> addProductStock(@PathVariable("warehouseId") UUID id) {
        log.info("Add product stock in inventory by warehouse_id: {}", id);
        return ResponseEntity.ok("get Inventory");
    }

    @PostMapping(path = "by_warehouse/{warehouseId}/reduce_product_stock")
    public ResponseEntity<String> reduceProductStock(@PathVariable("warehouseId") UUID id) {
        log.info("Reduce product stock in inventory by warehouse_id: {}", id);
        return ResponseEntity.ok("get Inventory");
    }
}
