package com.multiwarehouse.app.warehouse.service.domain.port.output.repository;

import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.domain.entity.Stock;

import java.util.List;

public interface StockRepository {

    List<Stock> findByWarehouseId(WarehouseId warehouseId);
}
