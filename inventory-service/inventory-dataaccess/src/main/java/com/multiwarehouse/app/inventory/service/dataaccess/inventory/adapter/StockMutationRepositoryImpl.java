package com.multiwarehouse.app.stockMutation.service.dataaccess.stockMutation.adapter;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.entity.StockMutationEntity;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.mapper.StockMutationDataAccessMapper;
import com.multiwarehouse.app.inventory.service.dataaccess.inventory.repository.StockMutationJpaRepository;
import com.multiwarehouse.app.inventory.service.domain.entity.StockMutation;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockMutationId;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
public class StockMutationRepositoryImpl implements StockMutationRepository {

    private final StockMutationJpaRepository stockMutationJpaRepository;
    private final StockMutationDataAccessMapper stockMutationDataAccessMapper;

    public StockMutationRepositoryImpl(StockMutationJpaRepository stockMutationJpaRepository,
                                   StockMutationDataAccessMapper stockMutationDataAccessMapper) {
        this.stockMutationJpaRepository = stockMutationJpaRepository;
        this.stockMutationDataAccessMapper = stockMutationDataAccessMapper;
    }

    @Override
    public Optional<StockMutation> findByWarehouseId(WarehouseId warehouseId) {
        return stockMutationJpaRepository.findByWarehouseId(warehouseId.getValue()).map(stockMutationDataAccessMapper::stockMutationFromStockMutationEntity);
    }

    @Override
    public Optional<StockMutation> findById(StockMutationId stockMutationId) {
        return stockMutationJpaRepository.findById(stockMutationId.getValue()).map(stockMutationDataAccessMapper::stockMutationFromStockMutationEntity);
    }

    @Override
    public StockMutation save(StockMutation stockMutation) {
        return stockMutationDataAccessMapper.stockMutationFromStockMutationEntity(stockMutationJpaRepository
                .save(stockMutationDataAccessMapper.stockMutationEntityFromStockMutation(stockMutation)));
    }

    @Override
    public void hardDelete(StockMutation stockMutation) {
        stockMutationJpaRepository.delete(stockMutationDataAccessMapper.stockMutationEntityFromStockMutation(stockMutation));
    }

    @Override
    public void softDelete(StockMutation stockMutation) {
        StockMutationEntity stockMutationEntity = stockMutationDataAccessMapper.stockMutationEntityFromStockMutation(stockMutation);
        stockMutationEntity.setDeletedAt(Instant.now());
        stockMutationJpaRepository.save(stockMutationEntity);
    }
}
