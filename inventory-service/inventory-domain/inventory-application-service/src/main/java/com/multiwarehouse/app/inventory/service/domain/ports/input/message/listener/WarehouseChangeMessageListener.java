package com.multiwarehouse.app.inventory.service.domain.ports.input.message.listener;

import com.multiwarehouse.app.inventory.service.domain.entity.Warehouse;

public interface WarehouseChangeMessageListener {
    void warehouseCreated(Warehouse warehouse);
    void warehouseUpdated(Warehouse warehouse);
    void warehouseSoftDeleted(Warehouse warehouse);
    void warehouseHardDeleted(Warehouse warehouse);
}
