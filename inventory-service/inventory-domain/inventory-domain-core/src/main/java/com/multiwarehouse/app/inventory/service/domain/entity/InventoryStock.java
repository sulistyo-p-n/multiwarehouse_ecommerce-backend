package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;
import com.multiwarehouse.app.inventory.service.domain.valueobject.InventoryStockId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InventoryStock extends BaseEntity<InventoryStockId> {
    private InventoryId inventoryId;
    private final Product product;
    private int quantity;

    private final List<StockJournal> journals;

    private InventoryStock(Builder builder) {
        super.setId(builder.id);
        inventoryId = builder.inventoryId;
        product = builder.product;
        quantity = builder.quantity;
        journals = builder.journals;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void initialize(InventoryId inventoryId) {
        this.inventoryId = inventoryId;
        setId(new InventoryStockId(UUID.randomUUID()));
        initializeJournals();
    }

    private void initializeJournals() {
        journals.forEach(journal -> journal.initialize(getId()));
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
        if (!isQuantityValid()) throw new InventoryDomainException("ProductStock.Quantity is not valid!");
    }

    private boolean isQuantityValid() {
        return quantity == journals.stream().mapToInt(StockJournal::getQuantity).sum();
    }

    private void validateJournals() {
        journals.forEach(StockJournal::validate);
    }

    public void addStock(int amount) {
        if (amount <= 0) throw new InventoryDomainException("ProductStock.AddStock.Amount must be greater than zero!");
        addJournal(amount);
        quantity += amount;
    }

    public void reduceStock(int amount) {
        if (amount <= 0) throw new InventoryDomainException("ProductStock.ReduceStock.Amount must be greater than zero!");
        validateAvailableStock(amount);
        addJournal(-amount);
        quantity -= amount;
    }

    public void validateAvailableStock(int decreaseAmount) {
        if (getQuantity() < decreaseAmount) { throw new InventoryDomainException("ProductStock.Quantity is insufficient stock!"); }
    }

    private void addJournal(int amount) {
        journals.add(StockJournal.builder()
                .withId(new StockJournalId(UUID.randomUUID()))
                .withInventoryStockId(getId())
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

    public List<StockJournal> getJournals() {
        return journals;
    }

    public void setInventoryId(InventoryId inventoryId) {
        this.inventoryId = inventoryId;
    }

    public static final class Builder {
        private InventoryStockId id;
        private InventoryId inventoryId;
        private Product product;
        private int quantity = 0;
        private List<StockJournal> journals = new ArrayList<>();;

        private Builder() {
        }

        public Builder withId(InventoryStockId val) {
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

        public Builder withJournals(List<StockJournal> val) {
            journals = val;
            return this;
        }

        public InventoryStock build() {
            return new InventoryStock(this);
        }
    }
}
