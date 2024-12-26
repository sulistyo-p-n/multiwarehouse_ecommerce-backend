package com.multiwarehouse.app.product.service.dataaccess.product.mapper;

import com.multiwarehouse.app.domain.valueobject.StockId;
import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductStockEntity;
import com.multiwarehouse.app.product.service.domain.entity.ProductStock;
import org.springframework.stereotype.Component;

@Component
public class ProductStockDataAccessMapper {

    public ProductStock productStockEntityToProductStock(ProductStockEntity productStockEntity) {
        return ProductStock.builder()
                .withQuantity(productStockEntity == null ? 0 : productStockEntity.getQuantity())
                .build();
    }
}
