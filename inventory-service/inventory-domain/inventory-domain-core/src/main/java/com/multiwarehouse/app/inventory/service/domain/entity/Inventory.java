package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

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

    public void validateInitialization() {
        validateInitialId();
    }

    private void validateInitialId() {
        if (getId() != null) throw new InventoryDomainException("Inventory is not in correct state for initialization!");
    }

    public void initialize() {
        setId(new InventoryId(UUID.randomUUID()));
        initializeProductStocks();
    }

    private void initializeProductStocks() {
        productStocks.forEach(productStock -> productStock.initialize(getId()));
    }

    public void validate() {
        validateId();
        validateWarehouse();
        validateProductStocks();
    }

    private void validateId() {
        if (getId() == null) throw new InventoryDomainException("Inventory.Id cannot be null!");
    }

    private void validateWarehouse() {
        if (getWarehouse() == null) throw new InventoryDomainException("Inventory.Warehouse cannot be null!");
    }

    private void validateProductStocks() {
        if (getProductStocks() == null) throw new InventoryDomainException("Inventory.ProductStocks cannot be null!");
        productStocks.forEach(ProductStock::validate);
    }

    public void validateAvailableStock(Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Stock compare quantity must be greater than zero!");
        productStocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(stock -> {
                    stock.validateAvailableStock(quantity);
                }, () -> {
                    throw new InventoryDomainException("No stock found!");
                });
    }

    public void transferStock(Inventory targetInventory, Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Stock transfer quantity must be greater than zero!");
        productStocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(stock -> {
                    stock.reduceStock(quantity);
                    targetInventory.addStock(product, quantity);
                }, () -> {
                    throw new InventoryDomainException("No stock found!");
                });
    }

    public void addStock(Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Stock add quantity must be greater than zero!");
        productStocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(
                        stock -> stock.addStock(quantity),
                        () -> productStocks.add(ProductStock.builder()
                                        .withInventoryId(getId())
                                        .withProduct(product)
                                        .withQuantity(quantity)
                                .build())
                );
    }

    public void reduceStock(Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Stock reduce quantity must be greater than zero!");
        productStocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(
                        stock -> stock.reduceStock(quantity),
                        () -> {
                            throw new InventoryDomainException("No stock found!");
                        });
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public List<ProductStock> getProductStocks() {
        return productStocks;
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
