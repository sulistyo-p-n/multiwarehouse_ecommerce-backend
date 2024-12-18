package com.multiwarehouse.app.warehouse.service.domain.rest;

import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehousesResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/warehouses", produces = "application/vnd.api.v1+json")
public class WarehouseController {
    private final WarehouseApplicationService warehouseApplicationService;

    public WarehouseController(WarehouseApplicationService warehouseApplicationService) {
        this.warehouseApplicationService = warehouseApplicationService;
    }

//    @GetMapping
//    public ResponseEntity<String> getWarehouses() {
//        log.info("Getting warehouses");
//        return ResponseEntity.ok("test");
//    }
//
//    @PostMapping
//    public ResponseEntity<CreateWarehouseCommand> createWarehouse(@RequestBody CreateWarehouseCommand createWarehouseCommand) {
//        return ResponseEntity.ok(createWarehouseCommand);
//    }

    @GetMapping
    public ResponseEntity<GetWarehousesResponse> getWarehouses() {
        log.info("Getting warehouses");
        GetWarehousesResponse getWarehousesResponse = warehouseApplicationService.getWarehouses();
        log.info("Returning warehouses: {}", getWarehousesResponse.getWarehouses());
        return ResponseEntity.ok(getWarehousesResponse);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<GetWarehouseResponse> getWarehouse(@PathVariable("id") UUID id) {
        log.info("Getting warehouse by id: {}", id);
        GetWarehouseResponse getWarehouseResponse = warehouseApplicationService.getWarehouse(id);
        log.info("Returning warehouse: {}", getWarehouseResponse.getName());
        return ResponseEntity.ok(getWarehouseResponse);
    }

    @PostMapping
    public ResponseEntity<CreateWarehouseResponse> createWarehouse(@RequestHeader("user_id") UUID userId, @RequestBody CreateWarehouseCommand createWarehouseCommand) {
        log.info("Creating warehouse with name: {}", createWarehouseCommand.getName());
        CreateWarehouseResponse createWarehouseResponse = warehouseApplicationService.createWarehouse(userId, createWarehouseCommand);
        log.info("Warehouse created with name: {}", createWarehouseResponse.getName());
        return ResponseEntity.ok(createWarehouseResponse);

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UpdateWarehouseResponse> updateWarehouse(@PathVariable("id") UUID id, @RequestBody UpdateWarehouseCommand updateWarehouseCommand) {
        log.info("Updating warehouse with id: {}", id);
        UpdateWarehouseResponse updateWarehouseResponse = warehouseApplicationService.updateWarehouse(id, updateWarehouseCommand);
        log.info("Warehouse updated with data: {}", updateWarehouseResponse.getName());
        return ResponseEntity.ok(updateWarehouseResponse);

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteWarehouseResponse> deleteWarehouse(@PathVariable("id") UUID id) {
        log.info("Deleting warehouse with id: {}", id);
        DeleteWarehouseResponse deleteWarehouseResponse = warehouseApplicationService.deleteWarehouse(id);
        log.info("Warehouse deleted with id: {}", id);
        return ResponseEntity.ok(deleteWarehouseResponse);

    }
}
