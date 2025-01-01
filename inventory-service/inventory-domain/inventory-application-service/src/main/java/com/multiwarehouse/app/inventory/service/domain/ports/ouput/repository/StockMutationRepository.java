package com.multiwarehouse.app.inventory.service.domain.ports.ouput.repository;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationId;

import java.time.Instant;
import java.util.Optional;

public interface StockMutationRepository {
    Optional<StockMutation> findByWarehouseId(WarehouseId warehouseId);
    Optional<StockMutation> findById(StockMutationId stockMutationId);
    StockMutation save(StockMutation stockMutation);
    void hardDelete(StockMutation stockMutation);
    void softDelete(StockMutation stockMutation);
}
