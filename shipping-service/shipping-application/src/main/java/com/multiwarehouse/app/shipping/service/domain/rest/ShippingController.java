package com.multiwarehouse.app.shipping.service.domain.rest;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/shippings", produces = "application/vnd.api.v1+json")
public class ShippingController {
//    private final ShippingApplicationService shippingApplicationService;
//
//    public ShippingController(ShippingApplicationService shippingApplicationService) {
//        this.shippingApplicationService = shippingApplicationService;
//    }

    public ShippingController() {
    }

    @GetMapping
    public ResponseEntity<String> getShippings() {
        log.info("Getting shippings");
        return ResponseEntity.ok("test");
    }

}
