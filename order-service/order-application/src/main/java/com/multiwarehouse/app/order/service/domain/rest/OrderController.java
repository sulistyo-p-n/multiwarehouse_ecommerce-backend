package com.multiwarehouse.app.order.service.domain.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/orders", produces = "application/vnd.api.v1+json")
public class OrderController {

    public OrderController() {
    }

    @GetMapping
    public ResponseEntity<String> getOrders() {
        return ResponseEntity.ok("test");
    }
}
