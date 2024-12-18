package com.multiwarehouse.app.warehouse.service.domain.port.input.message.listener.stock;

import com.multiwarehouse.app.warehouse.service.domain.dto.message.stock.StockResponse;

public interface StockCreatedMessageListener {

    void stockUpdated(StockResponse stockResponse);
}
