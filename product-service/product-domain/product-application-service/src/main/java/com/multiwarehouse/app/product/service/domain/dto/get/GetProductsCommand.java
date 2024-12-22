package com.multiwarehouse.app.product.service.domain.dto.get;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class GetProductsCommand {
    private final Boolean withInactive;
    private final Boolean withTrashed;
    private final String search;
}
