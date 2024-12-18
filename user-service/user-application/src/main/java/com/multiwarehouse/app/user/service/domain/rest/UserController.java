package com.multiwarehouse.app.user.service.domain.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/users", produces = "application/vnd.api.v1+json")
public class UserController {
//    private final UserApplicationService userApplicationService;
//
//    public UserController(UserApplicationService userApplicationService) {
//        this.userApplicationService = userApplicationService;
//    }

    public UserController() {
    }

    @GetMapping
    public ResponseEntity<String> getWarehouses() {
        log.info("Getting warehouses");
        return ResponseEntity.ok("test");
    }

}
