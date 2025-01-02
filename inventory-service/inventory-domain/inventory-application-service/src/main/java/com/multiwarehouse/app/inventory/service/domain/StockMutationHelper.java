package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryNotFoundException;
import com.multiwarehouse.app.inventory.service.domain.ports.ouput.repository.StockMutationRepository;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class StockMutationHelper {
    private final StockMutationRepository stockMutationRepository;

    public StockMutationHelper(StockMutationRepository stockMutationRepository) {
        this.stockMutationRepository = stockMutationRepository;
    }

    public List<StockMutation> findStockMutationsByWarehouseId(WarehouseId warehouseId) {
        return stockMutationRepository.findByWarehouseId(warehouseId);
    }

    public StockMutation findStockMutationById(StockMutationId stockMutationId) {
        Optional<StockMutation> stockMutation = stockMutationRepository.findById(stockMutationId);
        if (stockMutation.isEmpty()) {
            log.warn("Couldn't find StockMutation with id: {} ", stockMutationId.getValue());
            throw new InventoryNotFoundException("Couldn't find StockMutation with id: " + stockMutationId.getValue());
        }
        return stockMutation.get();
    }

    public StockMutation saveStockMutation(StockMutation stockMutation) {
        StockMutation stockMutationSaved = stockMutationRepository.save(stockMutation);
        if (stockMutationSaved == null) {
            log.error("Couldn't save StockMutation!");
            throw new InventoryDomainException("Cloud not save StockMutation!");
        }
        log.info("StockMutation is saved with id : {}", stockMutationSaved.getId().getValue());
        return stockMutationSaved;
    }

    public void deleteStockMutation(StockMutation stockMutation, Boolean forceDelete) {
        try {
            if (forceDelete != null && forceDelete) {
                stockMutationRepository.hardDelete(stockMutation);
            } else {
                stockMutationRepository.softDelete(stockMutation);
            }
        } catch (Exception e) {
            throw new InventoryDomainException("Cloud not delete StockMutation!");
        }
        log.info("StockMutation is deleted with id : {}", stockMutation.getId().getValue());
    }
}
