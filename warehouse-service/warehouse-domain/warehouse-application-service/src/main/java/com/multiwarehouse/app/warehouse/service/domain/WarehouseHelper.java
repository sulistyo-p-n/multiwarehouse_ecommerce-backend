package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.dto.get.GetWarehousesCommand;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.exception.WarehouseDomainException;
import com.multiwarehouse.app.warehouse.service.domain.exception.WarehouseNotFoundException;
import com.multiwarehouse.app.warehouse.service.domain.port.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class WarehouseHelper {
    private final WarehouseRepository warehouseRepository;

    public WarehouseHelper(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public List<Warehouse> findWarehouses() {
        return warehouseRepository.findAll();
    }

    public List<Warehouse> findWarehouses(GetWarehousesCommand getWarehousesCommand) {
        return warehouseRepository.findByCriteria(
                getWarehousesCommand.getWithInactive(),
                getWarehousesCommand.getWithTrashed(),
                getWarehousesCommand.getSearch());
    }

    public Warehouse findWarehouseById(WarehouseId warehouseId) {
        Optional<Warehouse> warehouseCategory = warehouseRepository.findById(warehouseId);
        if (warehouseCategory.isEmpty()) {
            log.warn("Couldn't find Warehouse with id: {} ", warehouseId.getValue());
            throw new WarehouseNotFoundException("Couldn't find Warehouse with id: " + warehouseId.getValue());
        }
        return warehouseCategory.get();
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        Warehouse warehouseCategoryResult = warehouseRepository.save(warehouse);
        if (warehouseCategoryResult == null) {
            log.error("Couldn't save Warehouse Category!");
            throw new WarehouseDomainException("Cloud not save Warehouse!");
        }
        log.info("Warehouse is saved with id : {}", warehouseCategoryResult.getId().getValue());
        return warehouseCategoryResult;
    }

    public void deleteWarehouse(Warehouse warehouse, Boolean forceDelete) {
        try {
            if (forceDelete != null && forceDelete) {
                warehouseRepository.hardDelete(warehouse);
            } else {
                warehouseRepository.softDelete(warehouse);
            }
        } catch (Exception e) {
            throw new WarehouseDomainException("Cloud not delete Warehouse!");
        }
        log.info("Warehouse is deleted with id : {}", warehouse.getId().getValue());
    }
}
