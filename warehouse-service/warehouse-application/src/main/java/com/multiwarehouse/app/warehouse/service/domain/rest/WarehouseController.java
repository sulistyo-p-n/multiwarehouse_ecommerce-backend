package com.multiwarehouse.app.warehouse.service.domain.rest;

import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.delete.DeleteWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehousesCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.dto.update.UpdateWarehouseResponse;
import com.multiwarehouse.app.warehouse.service.domain.port.input.service.WarehouseApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/warehouses", produces = "application/vnd.api.v1+json")
public class WarehouseController {
    private final WarehouseApplicationService warehouseApplicationService;

    public WarehouseController(WarehouseApplicationService warehouseApplicationService) {
        this.warehouseApplicationService = warehouseApplicationService;
    }

    @GetMapping
    public ResponseEntity<List<GetWarehouseResponse>> getWarehouses(GetWarehousesCommand getWarehousesCommand) {
        log.info("Getting warehouses: {}", getWarehousesCommand);
        List<GetWarehouseResponse> getWarehousesResponse = warehouseApplicationService.getWarehouses(getWarehousesCommand);
        log.info("Returning warehouses size: {}", getWarehousesResponse.size());
        return ResponseEntity.ok(getWarehousesResponse);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<GetWarehouseResponse> getWarehouse(@PathVariable("id") UUID id) {
        log.info("Getting warehouse by id: {}", id);
        GetWarehouseCommand getWarehouseCommand = GetWarehouseCommand.builder().id(id).build();
        GetWarehouseResponse getWarehouseResponse = warehouseApplicationService.getWarehouse(getWarehouseCommand);
        log.info("Returning warehouse: {}", getWarehouseResponse);
        return ResponseEntity.ok(getWarehouseResponse);
    }

    @PostMapping
    public ResponseEntity<CreateWarehouseResponse> createWarehouse(@RequestBody CreateWarehouseCommand createWarehouseCommand) {
        log.info("Creating warehouse with name: {}", createWarehouseCommand.getName());
        CreateWarehouseResponse createWarehouseResponse = warehouseApplicationService.createWarehouse(createWarehouseCommand);
        log.info("Warehouse created with id: {}", createWarehouseResponse.getId());
        return ResponseEntity.ok(createWarehouseResponse);

    }

    @PutMapping(path = "{id}")
    public ResponseEntity<UpdateWarehouseResponse> updateWarehouse(@PathVariable("id") UUID id, @RequestBody UpdateWarehouseCommand updateWarehouseCommand) {
        log.info("Updating warehouse with id: {}", id);
        updateWarehouseCommand.setId(id);
        UpdateWarehouseResponse updateWarehouseResponse = warehouseApplicationService.updateWarehouse(updateWarehouseCommand);
        log.info("Warehouse updated with: {}", updateWarehouseResponse);
        return ResponseEntity.ok(updateWarehouseResponse);

    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<DeleteWarehouseResponse> deleteWarehouse(@PathVariable("id") UUID id, @RequestBody Optional<DeleteWarehouseCommand> deleteWarehouseCommand) {
        log.info("Deleting warehouse with id: {}", id);
        DeleteWarehouseCommand deleteWarehouseCommandWithId = deleteWarehouseCommand.orElse(DeleteWarehouseCommand.builder().build());
        deleteWarehouseCommandWithId.setId(id);
        DeleteWarehouseResponse deleteWarehouseResponse = warehouseApplicationService.deleteWarehouse(deleteWarehouseCommandWithId);
        log.info("Warehouse deleted with id: {}", id);
        return ResponseEntity.ok(deleteWarehouseResponse);

    }
}
