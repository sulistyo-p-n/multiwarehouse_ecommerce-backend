package com.multiwarehouse.app.authgateway.service.domain.port.input.service;

import com.multiwarehouse.app.authgateway.service.domain.entity.User;

public interface AuthApplicationService {
    User validateUser(String token);
    User validateAdmin(String token);
    User validateSuperAdmin(String token);
    User validateWarehouseAdmin(String token, String warehouseId);
}
