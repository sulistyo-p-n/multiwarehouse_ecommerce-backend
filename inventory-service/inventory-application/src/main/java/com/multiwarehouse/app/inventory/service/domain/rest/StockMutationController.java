package com.multiwarehouse.app.inventory.service.domain.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/stock_mutations", produces = "application/vnd.api.v1+json")
public class StockMutationController {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("get stock mutation");
    }

    @GetMapping(path = "by_warehouse/{warehouseId}")
    public ResponseEntity<String> getStockMutations(@PathVariable("warehouseId") UUID id) {
        log.info("Getting stock mutations by warehouse_id: {}", id);
        return ResponseEntity.ok("get stock mutations");
    }

    @PostMapping(path = "by_warehouse/{warehouseId}")
    public ResponseEntity<String> createStockMutation(@PathVariable("warehouseId") UUID id) {
        log.info("Creating stock mutation by warehouse_id: {}", id);
        return ResponseEntity.ok("create stock mutations");
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<String> getStockMutation(@PathVariable("id") UUID id) {
        log.info("Getting stock mutation by id: {}", id);
        return ResponseEntity.ok("get stock mutation");
    }

    @PutMapping(path = "{id}/accept")
    public ResponseEntity<String> acceptStockMutation(@PathVariable("id") UUID id) {
        log.info("Accepting stock mutation by id: {}", id);
        return ResponseEntity.ok("accept stock mutation");
    }

    @PutMapping(path = "{id}/reject")
    public ResponseEntity<String> rejectStockMutation(@PathVariable("id") UUID id) {
        log.info("Rejecting stock mutation by id: {}", id);
        return ResponseEntity.ok("reject stock mutation");
    }
}
