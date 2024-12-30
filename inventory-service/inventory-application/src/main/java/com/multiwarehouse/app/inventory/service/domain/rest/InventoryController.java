package com.multiwarehouse.app.inventory.service.domain.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/inventories", produces = "application/vnd.api.v1+json")
public class InventoryController {

    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("get Inventory");
    }
}
