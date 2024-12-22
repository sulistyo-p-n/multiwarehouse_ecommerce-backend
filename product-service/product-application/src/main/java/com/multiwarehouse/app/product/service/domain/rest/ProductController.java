package com.multiwarehouse.app.product.service.domain.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/products", produces = "application/vnd.api.v1+json")
public class ProductController {
}
