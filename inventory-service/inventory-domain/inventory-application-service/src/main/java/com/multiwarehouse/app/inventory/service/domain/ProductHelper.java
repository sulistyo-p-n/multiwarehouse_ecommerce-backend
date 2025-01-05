package com.multiwarehouse.app.inventory.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.inventory.service.domain.entity.Product;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryDomainException;
import com.multiwarehouse.app.inventory.service.domain.exception.InventoryNotFoundException;
import com.multiwarehouse.app.inventory.service.domain.ports.output.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class ProductHelper {
    private final ProductRepository productRepository;

    public ProductHelper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findProductById(ProductId productId) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            log.warn("Couldn't find Product with id: {} ", productId.getValue());
            throw new InventoryNotFoundException("Couldn't find Product with id: " + productId.getValue());
        }
        return product.get();
    }

    public Product saveProduct(Product product) {
        Product productSaved = productRepository.save(product);
        if (productSaved == null) {
            log.error("Couldn't save Product!");
            throw new InventoryDomainException("Cloud not save Product!");
        }
        log.info("Product is saved with id : {}", productSaved.getId().getValue());
        return productSaved;
    }

    public void deleteProduct(Product product, Boolean forceDelete) {
        try {
            if (forceDelete != null && forceDelete) {
                productRepository.hardDelete(product);
            } else {
                productRepository.softDelete(product);
            }
        } catch (Exception e) {
            throw new InventoryDomainException("Cloud not delete Product!");
        }
        log.info("Product is deleted with id : {}", product.getId().getValue());
    }
}
