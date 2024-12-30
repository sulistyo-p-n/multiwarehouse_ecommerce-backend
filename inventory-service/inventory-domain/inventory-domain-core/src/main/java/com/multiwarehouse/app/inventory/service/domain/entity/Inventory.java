package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.InventoryId;

import java.util.List;

public class Inventory extends AggregateRoot<InventoryId> {
    private final Warehouse warehouse;
    private final List<ProductStock> productStocks;

    private Inventory(Builder builder) {
        super.setId(builder.id);
        warehouse = builder.warehouse;
        productStocks = builder.productStocks;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public List<ProductStock> getProductStocks() {
        return productStocks;
    }

    public void addStock(Product product, int quantity) {
        productStocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(
                        stock -> stock.increaseStock(quantity),
                        () -> productStocks.add(ProductStock.builder()
                                        .withInventoryId(getId())
                                        .withProduct(product)
                                        .withQuantity(quantity)
                                .build())
                );
    }

    public void transferStock(Inventory targetInventory, Product product, int quantity) {
        productStocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst()
                .ifPresent(stock -> {
                    stock.decreaseStock(quantity);
                    targetInventory.addStock(product, quantity);
                });
    }

    public static final class Builder {
        private InventoryId id;
        private Warehouse warehouse;
        private List<ProductStock> productStocks;

        private Builder() {
        }

        public Builder withId(InventoryId val) {
            id = val;
            return this;
        }

        public Builder withWarehouse(Warehouse val) {
            warehouse = val;
            return this;
        }

        public Builder withProductStocks(List<ProductStock> val) {
            productStocks = val;
            return this;
        }

        public Inventory build() {
            return new Inventory(this);
        }
    }
}
