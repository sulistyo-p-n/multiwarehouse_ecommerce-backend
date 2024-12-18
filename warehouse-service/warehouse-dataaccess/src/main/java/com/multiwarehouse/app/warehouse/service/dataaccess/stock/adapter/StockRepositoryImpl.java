package com.multiwarehouse.app.warehouse.service.dataaccess.stock.adapter;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.dataaccess.stock.mapper.StockDataAccessMapper;
import com.multiwarehouse.app.warehouse.service.dataaccess.stock.repository.StockJpaRepository;
import com.multiwarehouse.app.warehouse.service.domain.entity.Stock;
import com.multiwarehouse.app.warehouse.service.domain.port.output.repository.StockRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockRepositoryImpl implements StockRepository {

    private final StockJpaRepository stockJpaRepository;
    private final StockDataAccessMapper stockDataAccessMapper;

    public StockRepositoryImpl(StockJpaRepository stockJpaRepository,
                               StockDataAccessMapper stockDataAccessMapper) {
        this.stockJpaRepository = stockJpaRepository;
        this.stockDataAccessMapper = stockDataAccessMapper;
    }

    @Override
    public List<Stock> findByWarehouseId(WarehouseId warehouseId) {
        return stockJpaRepository.findByWarehouseId(warehouseId.getValue())
                .stream()
                .map(stockDataAccessMapper::stockEntityToStock)
                .toList();
    }
}
