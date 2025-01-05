package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.user.service.domain.entity.Warehouse;
import com.multiwarehouse.app.user.service.domain.exception.UserDomainException;
import com.multiwarehouse.app.user.service.domain.exception.UserNotFoundException;
import com.multiwarehouse.app.user.service.domain.ports.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class WarehouseHelper {
    private final com.multiwarehouse.app.user.service.domain.ports.output.repository.WarehouseRepository warehouseRepository;

    public WarehouseHelper(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }
    
    public Warehouse findWarehouseById(WarehouseId warehouseId) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseId);
        if (warehouse.isEmpty()) {
            log.warn("Couldn't find Warehouse with id: {} ", warehouseId.getValue());
            throw new UserNotFoundException("Couldn't find Warehouse with id: " + warehouseId.getValue());
        }
        return warehouse.get();
    }

    public Warehouse saveWarehouse(Warehouse warehouse) {
        Warehouse warehouseSaved = warehouseRepository.save(warehouse);
        if (warehouseSaved == null) {
            log.error("Couldn't save Warehouse!");
            throw new UserDomainException("Cloud not save Warehouse!");
        }
        log.info("Warehouse is saved with id : {}", warehouseSaved.getId().getValue());
        return warehouseSaved;
    }

    public void deleteWarehouse(Warehouse warehouse, Boolean forceDelete) {
        try {
            if (forceDelete != null && forceDelete) {
                warehouseRepository.hardDelete(warehouse);
            } else {
                warehouseRepository.softDelete(warehouse);
            }
        } catch (Exception e) {
            throw new UserDomainException("Cloud not delete Warehouse!");
        }
        log.info("Warehouse is deleted with id : {}", warehouse.getId().getValue());
    }
}
