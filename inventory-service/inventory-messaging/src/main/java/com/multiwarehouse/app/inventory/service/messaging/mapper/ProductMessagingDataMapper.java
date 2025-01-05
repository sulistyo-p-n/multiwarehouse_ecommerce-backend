package com.multiwarehouse.app.inventory.service.messaging.mapper;

import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.kafka.product.avro.model.ProductChangeAvroModel;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductMessagingDataMapper {
    public Product productFromProductChangeAvroModel(ProductChangeAvroModel productChangeAvroModel) {
        return Product.builder()
                .withId(new ProductId(productChangeAvroModel.getProductId()))
                .withProductCategoryId(new ProductCategoryId(productChangeAvroModel.getCategoryId()))
                .withCode(productChangeAvroModel.getCode())
                .withName(productChangeAvroModel.getName())
                .withPrice(new Money(productChangeAvroModel.getPrice()))
                .withActive(productChangeAvroModel.getActive())
                .build();
    }
}
