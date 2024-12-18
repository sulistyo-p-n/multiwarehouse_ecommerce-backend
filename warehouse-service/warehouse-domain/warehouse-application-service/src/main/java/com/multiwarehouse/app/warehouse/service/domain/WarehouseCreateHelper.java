package com.multiwarehouse.app.warehouse.service.domain;

import com.multiwarehouse.app.domain.valueobject.UserId;
import com.multiwarehouse.app.warehouse.service.domain.dto.create.CreateWarehouseCommand;
import com.multiwarehouse.app.warehouse.service.domain.entity.User;
import com.multiwarehouse.app.warehouse.service.domain.entity.Warehouse;
import com.multiwarehouse.app.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.multiwarehouse.app.warehouse.service.domain.exception.WarehouseDomainException;
import com.multiwarehouse.app.warehouse.service.domain.mapper.WarehouseDataMapper;
import com.multiwarehouse.app.warehouse.service.domain.port.output.message.publisher.WarehouseCreatedMessagePublisher;
import com.multiwarehouse.app.warehouse.service.domain.port.output.repository.UserRepository;
import com.multiwarehouse.app.warehouse.service.domain.port.output.repository.WarehouseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class WarehouseCreateHelper {
    private final WarehouseDomainService warehouseDomainService;
    private final WarehouseRepository warehouseRepository;
    private final UserRepository userRepository;
    private final WarehouseDataMapper warehouseDataMapper;
    private final WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher;

    public WarehouseCreateHelper(WarehouseDomainService warehouseDomainService, WarehouseRepository warehouseRepository, UserRepository userRepository, WarehouseDataMapper warehouseDataMapper, WarehouseCreatedMessagePublisher warehouseCreatedMessagePublisher) {
        this.warehouseDomainService = warehouseDomainService;
        this.warehouseRepository = warehouseRepository;
        this.userRepository = userRepository;
        this.warehouseDataMapper = warehouseDataMapper;
        this.warehouseCreatedMessagePublisher = warehouseCreatedMessagePublisher;
    }

    @Transactional
    public WarehouseCreatedEvent persistWarehouse(UUID userId, CreateWarehouseCommand createWarehouseCommand) {
        User user = checkUser(userId);
        Warehouse warehouse = warehouseDataMapper.createWarehouseCommandToWarehouse(createWarehouseCommand);
        WarehouseCreatedEvent warehouseCreatedEvent = warehouseDomainService.validationAndInitiateWarehouse(warehouse, user, warehouseCreatedMessagePublisher);
        Warehouse warehouseSaved = saveWarehouse(warehouse);
        log.info("Warehouse is created with id : {}", warehouseCreatedEvent.getWarehouse().getId().getValue());
        return warehouseCreatedEvent;
    }

    private User checkUser(UUID userId) {
        Optional<User> user = userRepository.findById(new UserId(userId));
        if (user.isPresent()) return user.get();
        log.warn("Could not find user with user id: {}", userId);
        throw new WarehouseDomainException("Could not find user with user id: " + userId);
    }

    private Warehouse saveWarehouse(Warehouse warehouse) {
        Warehouse warehouseResult = warehouseRepository.save(warehouse);
        if (warehouseResult == null) {
            log.error("Could not save warehouse!");
            throw new WarehouseDomainException("Cloud not save warehouse!");
        }
        log.info("Warehouse is saved with id : {}", warehouseResult.getId().getValue());
        return warehouseResult;
    }
}
