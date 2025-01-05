package com.multiwarehouse.app.user.service.domain;

import com.multiwarehouse.app.user.service.domain.entity.Warehouse;
import com.multiwarehouse.app.user.service.domain.ports.input.message.listener.WarehouseChangeMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
@Validated
public class WarehouseChangeMessageListenerImpl implements WarehouseChangeMessageListener {
    private final WarehouseHelper warehouseHelper;

    public WarehouseChangeMessageListenerImpl(WarehouseHelper warehouseHelper) {
        this.warehouseHelper = warehouseHelper;
    }

    @Override
    public void warehouseCreated(Warehouse warehouse) {
        warehouseHelper.saveWarehouse(warehouse);
    }

    @Override
    public void warehouseUpdated(Warehouse warehouse) {
        warehouseHelper.saveWarehouse(warehouse);
    }

    @Override
    public void warehouseSoftDeleted(Warehouse warehouse) {
        warehouseHelper.deleteWarehouse(warehouse, false);
    }

    @Override
    public void warehouseHardDeleted(Warehouse warehouse) {
        warehouseHelper.deleteWarehouse(warehouse, true);
    }
}
