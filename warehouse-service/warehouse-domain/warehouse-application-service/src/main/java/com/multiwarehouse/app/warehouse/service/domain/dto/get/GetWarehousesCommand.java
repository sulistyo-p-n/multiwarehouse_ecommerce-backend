package com.multiwarehouse.app.warehouse.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class GetWarehousesCommand {
    private final Boolean withInactive;
    private final Boolean withTrashed;
    private final String search;
}
