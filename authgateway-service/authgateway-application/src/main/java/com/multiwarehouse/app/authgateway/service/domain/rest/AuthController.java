package com.multiwarehouse.app.authgateway.service.domain.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/auth", produces = "application/vnd.api.v1+json")
public class AuthController {

    @GetMapping
    public ResponseEntity<String> get() {
        log.info("Get Auth");
        return ResponseEntity.ok("Get Auth");
    }
}
