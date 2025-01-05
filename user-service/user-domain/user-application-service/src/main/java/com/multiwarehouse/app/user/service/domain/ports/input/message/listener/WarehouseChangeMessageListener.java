package com.multiwarehouse.app.user.service.domain.ports.input.message.listener;

import com.multiwarehouse.app.user.service.domain.entity.Warehouse;

public interface WarehouseChangeMessageListener {
    void warehouseCreated(Warehouse warehouse);
    void warehouseUpdated(Warehouse warehouse);
    void warehouseSoftDeleted(Warehouse warehouse);
    void warehouseHardDeleted(Warehouse warehouse);
}
