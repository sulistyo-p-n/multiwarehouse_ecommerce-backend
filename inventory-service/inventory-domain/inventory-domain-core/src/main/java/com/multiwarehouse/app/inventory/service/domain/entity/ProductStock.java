package com.multiwarehouse.app.inventory.service.domain.entity;

import com.multiwarehouse.app.domain.entity.BaseEntity;
import com.multiwarehouse.app.domain.valueobject.InventoryId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.ProductStockId;
import com.multiwarehouse.app.inventory.service.domain.valueobject.StockJournalType;

import java.util.ArrayList;
import java.util.List;

public class ProductStock extends BaseEntity<ProductStockId> {
    private final InventoryId inventoryId;
    private final Product product;
    private Integer quantity;

    private List<StockJournal> journals;

    private ProductStock(Builder builder) {
        super.setId(builder.id);
        inventoryId = builder.inventoryId;
        product = builder.product;
        quantity = builder.quantity;
        journals = builder.journals;
    }

    public static Builder builder() {
        return new Builder();
    }

    public InventoryId getInventoryId() {
        return inventoryId;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public List<StockJournal> getJournals() {
        return journals;
    }

    public void increaseStock(int amount) {
        addAddictionStockJournal(amount);
        quantity += amount;
    }

    private void addAddictionStockJournal(int amount) {
        if (journals == null) journals = new ArrayList<>();
        journals.add(StockJournal.builder()
                .withProductStockId(getId())
                .withQuantity(amount)
                .withType(StockJournalType.ADDICTION)
                .build());
    }

    public void decreaseStock(int amount) {
        if (getQuantity() < amount) { throw new IllegalArgumentException("Insufficient stock!"); }
        addReductionStockJournal(amount);
        quantity -= amount;
    }

    private void addReductionStockJournal(int amount) {
        if (journals == null) journals = new ArrayList<>();
        journals.add(StockJournal.builder()
                .withProductStockId(getId())
                .withQuantity(amount)
                .withType(StockJournalType.REDUCTION)
                .build());
    }

    public static final class Builder {
        private ProductStockId id;
        private InventoryId inventoryId;
        private Product product;
        private Integer quantity;
        private List<StockJournal> journals;

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

        public Builder withQuantity(Integer val) {
            quantity = val;
            return this;
        }

        public Builder withJournals(List<StockJournal> val) {
            journals = val;
            return this;
        }

        public ProductStock build() {
            return new ProductStock(this);
        }
    }
}
