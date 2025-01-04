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
    private final List<ProductStock> productStocks;
    private final Boolean active;

    private Inventory(Builder builder) {
        super.setId(builder.id);
        active = builder.active;
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
        if (getId() != null) throw new InventoryDomainException("Inventory.Id is not in correct state for initialization!");
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
        validateActive();
        validateWarehouse();
        validateProductStocks();
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

    private void validateProductStocks() {
        if (productStocks == null) throw new InventoryDomainException("Inventory.ProductStocks cannot be null!");
        productStocks.forEach(ProductStock::validate);
    }

    public void validateAvailableStock(Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Inventory.Input.Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Inventory.Input.Quantity must be greater than zero!");
        productStocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(stock -> {
                    stock.validateAvailableStock(quantity);
                }, () -> {
                    throw new InventoryDomainException("Inventory.ProductStock No stock found!");
                });
    }

    public void transferStock(Inventory targetInventory, Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Inventory.Input.Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Inventory.Input.Quantity must be greater than zero!");
        productStocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst()
                .ifPresentOrElse(stock -> {
                    stock.reduceStock(quantity);
                    targetInventory.addStock(product, quantity);
                }, () -> {
                    throw new InventoryDomainException("Inventory.ProductStock No stock found!");
                });
    }

    public void addStock(Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Inventory.Input.Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Inventory.Input.Quantity must be greater than zero!");
        Optional<ProductStock> filteredProductStock = productStocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst();

        ProductStock productStock;
        if (filteredProductStock.isEmpty()) {
            productStock = ProductStock.builder()
                    .withProduct(product)
                    .build();
            productStock.initialize(getId());
            productStocks.add(productStock);
        } else {
            productStock = filteredProductStock.get();
        }

        productStock.addStock(quantity);
    }

    public void reduceStock(Product product, int quantity) {
        if (product == null) throw new InventoryDomainException("Inventory.Input.Product cannot be null!");
        if (quantity == 0) throw new InventoryDomainException("Inventory.Input.Quantity must be greater than zero!");

        Optional<ProductStock> filteredProductStock = productStocks.stream()
                .filter(stock -> stock.getProduct().equals(product))
                .findFirst();

        if (filteredProductStock.isEmpty()) throw new InventoryDomainException("Inventory.ProductStock No stock found!");

        ProductStock productStock = filteredProductStock.get();
        productStock.reduceStock(quantity);
    }

    public Boolean isActive() {
        return active;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public List<ProductStock> getProductStocks() {
        return productStocks;
    }

    public static final class Builder {
        private InventoryId id;
        private Boolean active;
        private Warehouse warehouse;
        private List<ProductStock> productStocks = new ArrayList<>();

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

        public Builder withProductStocks(List<ProductStock> val) {
            productStocks = val;
            return this;
        }

        public Inventory build() {
            return new Inventory(this);
        }
    }
}
