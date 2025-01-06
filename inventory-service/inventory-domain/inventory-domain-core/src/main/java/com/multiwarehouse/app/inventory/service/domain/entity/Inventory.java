package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.AggregateRoot;
import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Inventory extends AggregateRoot<InventoryId> {
    private final Warehouse warehouse;
    private final List<InventoryStock> stocks;
    private Boolean active;

    private Inventory(Builder builder) {
        super.setId(builder.id);
        active = builder.active;
        warehouse = builder.warehouse;
        stocks = builder.stocks;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void validateInitialization() {
        validateInitialId();
    }

    private void validateInitialId() {
        if (getId() != null) throw new InventoryDomainException("Inventory.Id is not in correct state for initialization!");
    }

    public void initialize() {
        setId(new InventoryId(UUID.randomUUID()));
        initializeStocks();
    }

    private void initializeStocks() {
        stocks.forEach(stock -> stock.initialize(getId()));
    }

    public void validate() {
        validateId();
        validateActive();
        validateWarehouse();
        validateStocks();
    }

    private void validateId() {
        if (getId() == null) throw new InventoryDomainException("Inventory.Id cannot be null!");
    }

    private void validateActive() {
        if (active == null) throw new InventoryDomainException("Inventory.Active cannot be null!");
    }

    private void validateWarehouse() {
        if (warehouse == null) throw new InventoryDomainException("Inventory.Warehouse cannot be null!");
    }

    private void validateStocks() {
        if (stocks == null) throw new InventoryDomainException("Inventory.Stocks cannot be null!");
        stocks.forEach(InventoryStock::validate);
    }

    public void validateAvailableStock(Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Inventory.Input.Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Inventory.Input.Quantity must be greater than zero!");
        stocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(stock -> {
                    stock.validateAvailableStock(quantity);
                }, () -> {
                    throw new InventoryDomainException("Inventory.Stock No stock found!");
                });
    }

    public void transferStock(Inventory targetInventory, Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Inventory.Input.Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Inventory.Input.Quantity must be greater than zero!");
        stocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(stock -> {
                    stock.reduceStock(quantity);
                    targetInventory.addStock(product, quantity);
                }, () -> {
                    throw new InventoryDomainException("Inventory.Stock No stock found!");
                });
    }

    public void addStock(Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Inventory.Input.Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Inventory.Input.Quantity must be greater than zero!");
        Optional<InventoryStock> filteredStock = stocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst();

        InventoryStock stock;
        if (filteredStock.isEmpty()) {
            stock = InventoryStock.builder()
                    .withProduct(product)
                    .build();
            stock.initialize(getId());
            stocks.add(stock);
        } else {
            stock = filteredStock.get();
        }

        stock.addStock(quantity);
    }

    public void reduceStock(Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Inventory.Input.Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Inventory.Input.Quantity must be greater than zero!");

        Optional<InventoryStock> filteredStock = stocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst();

        if (filteredStock.isEmpty()) throw new InventoryDomainException("Inventory.Stock No stock found!");

        InventoryStock stock = filteredStock.get();
        stock.reduceStock(quantity);
    }

    public void setActive(Boolean active) {
        if (active == null) return;
        this.active = active;
    }

    public Boolean isActive() {
        return active;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public List<InventoryStock> getStocks() {
        return stocks;
    }

    public static final class Builder {
        private InventoryId id;
        private Boolean active;
        private Warehouse warehouse;
        private List<InventoryStock> stocks = new ArrayList<>();

        private Builder() {
        }

        public Builder withId(InventoryId val) {
            id = val;
            return this;
        }

        public Builder withActive(Boolean val) {
            active = val;
            return this;
        }

        public Builder withWarehouse(Warehouse val) {
            warehouse = val;
            return this;
        }

        public Builder withStocks(List<InventoryStock> val) {
            stocks = val;
            return this;
        }

        public Inventory build() {
            return new Inventory(this);
        }
    }
}
