package com.multiwarehouse.app.warehouse.service.dataaccess.stock.mapper;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.domain.valueobject.StockId;
import com.multiwarehouse.app.domain.valueobject.WarehouseId;
import com.multiwarehouse.app.warehouse.service.dataaccess.stock.entity.StockEntity;
import com.multiwarehouse.app.warehouse.service.domain.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockDataAccessMapper {
    public Stock stockEntityToStock(StockEntity stockEntity) {
        return Stock.builder()
                .withId(new StockId(stockEntity.getId()))
                .withWarehouseId(new WarehouseId(stockEntity.getWarehouseId()))
                .withProductId(new ProductId(stockEntity.getProductId()))
                .withQuantity(stockEntity.getQuantity())
                .build();
    }
}
