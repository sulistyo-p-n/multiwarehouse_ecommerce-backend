package com.multiwarehouse.app.inventory.service.domain.ports.output.repository;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationId;

import java.util.List;
import java.util.Optional;

public interface StockMutationRepository {
    List<StockMutation> findByWarehouseId(WarehouseId warehouseId);
    Optional<StockMutation> findById(StockMutationId stockMutationId);
    StockMutation save(StockMutation stockMutation);
    void hardDelete(StockMutation stockMutation);
    void softDelete(StockMutation stockMutation);
}
