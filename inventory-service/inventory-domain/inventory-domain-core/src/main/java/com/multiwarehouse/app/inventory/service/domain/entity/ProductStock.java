package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;
import com.multiwarehouse.app.inventory.service.domain.valueobject.ProductStockId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductStock extends BaseEntity<ProductStockId> {
    private InventoryId inventoryId;
    private final Product product;
    private int quantity;

    private List<StockJournal> stockJournals;

    private ProductStock(Builder builder) {
        super.setId(builder.id);
        inventoryId = builder.inventoryId;
        product = builder.product;
        quantity = builder.quantity;
        stockJournals = builder.stockJournals;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initialize(InventoryId inventoryId) {
        this.inventoryId = inventoryId;
        setId(new ProductStockId(UUID.randomUUID()));
        initializeStockJournals();
    }

    private void initializeStockJournals() {
        stockJournals.forEach(stockJournal -> stockJournal.initialize(getId()));
    }

    public void validate() {
        validateId();
        validateInventoryId();
        validateProduct();
        validateQuantity();
        validateJournals();
    }

    private void validateId() {
        if (getId() == null) throw new InventoryDomainException("ProductStock.Id cannot be null!");
    }

    private void validateInventoryId() {
        if (inventoryId == null) throw new InventoryDomainException("ProductStock.InventoryId cannot be null!");
    }

    private void validateProduct() {
        if (product == null) throw new InventoryDomainException("ProductStock.Product cannot be null!");
    }

    private void validateQuantity() {
        if (quantity < 0) throw new InventoryDomainException("ProductStock.Quantity cannot be negative!");
        if (isQuantityValid()) throw new InventoryDomainException("ProductStock.Quantity is not valid!");
    }

    private boolean isQuantityValid() {
        return quantity == stockJournals.stream().mapToInt(StockJournal::getQuantity).sum();
    }

    private void validateJournals() {
        stockJournals.forEach(StockJournal::validate);
    }

    public void addStock(int amount) {
        if (amount <= 0) throw new InventoryDomainException("ProductStock.AddStock.Amount must be greater than zero!");
        addStockJournal(amount);
        quantity += amount;
    }

    public void reduceStock(int amount) {
        if (amount <= 0) throw new InventoryDomainException("ProductStock.ReduceStock.Amount must be greater than zero!");
        validateAvailableStock(amount);
        addStockJournal(-amount);
        quantity -= amount;
    }

    public void validateAvailableStock(int decreaseAmount) {
        if (getQuantity() < decreaseAmount) { throw new InventoryDomainException("ProductStock.Quantity is insufficient stock!"); }
    }

    private void addStockJournal(int amount) {
        if (stockJournals == null) stockJournals = new ArrayList<>();
        stockJournals.add(StockJournal.builder()
                .withId(new StockJournalId(UUID.randomUUID()))
                .withProductStockId(getId())
                .withQuantity(amount).withType((amount > 0) ? StockJournalType.ADDICTION : StockJournalType.REDUCTION)
                .build());
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public List<StockJournal> getStockJournals() {
        return stockJournals;
    }

    public void setInventoryId(InventoryId inventoryId) {
        this.inventoryId = inventoryId;
    }

    public static final class Builder {
        private ProductStockId id;
        private InventoryId inventoryId;
        private Product product;
        private int quantity;
        private List<StockJournal> stockJournals;

        private Builder() {
        }

        public Builder withId(ProductStockId val) {
            id = val;
            return this;
        }

        public Builder withInventoryId(InventoryId val) {
            inventoryId = val;
            return this;
        }

        public Builder withProduct(Product val) {
            product = val;
            return this;
        }

        public Builder withQuantity(int val) {
            quantity = val;
            return this;
        }

        public Builder withStockJournals(List<StockJournal> val) {
            stockJournals = val;
            return this;
        }

        public ProductStock build() {
            return new ProductStock(this);
        }
    }
}
