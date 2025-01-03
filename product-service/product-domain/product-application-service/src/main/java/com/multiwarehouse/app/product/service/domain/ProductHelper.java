package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductsCommand;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.exception.ProductCategoryDomainException;
import com.multiwarehouse.app.product.service.domain.exception.ProductCategoryNotFoundException;
import com.multiwarehouse.app.product.service.domain.exception.ProductDomainException;
import com.multiwarehouse.app.product.service.domain.exception.ProductNotFoundException;
import com.multiwarehouse.app.product.service.domain.ports.output.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ProductHelper {
    private final ProductRepository productRepository;

    public ProductHelper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findProducts() {
        return productRepository.findAll();
    }

    public List<Product> findProducts(GetProductsCommand getProductsCommand) {
        return productRepository.findByCriteria(
                getProductsCommand.getWithInactive(),
                getProductsCommand.getWithTrashed(),
                new ProductCategoryId(getProductsCommand.getProductCategoryId()),
                getProductsCommand.getSearch());
    }

    public Product findProductById(ProductId productId) {
        Optional<Product> productCategory = productRepository.findById(productId);
        if (productCategory.isEmpty()) {
            log.warn("Couldn't find Product with id: {} ", productId.getValue());
            throw new ProductNotFoundException("Couldn't find Product with id: " + productId.getValue());
        }
        return productCategory.get();
    }

    public Product saveProduct(Product product) {
        Product productCategoryResult = productRepository.save(product);
        if (productCategoryResult == null) {
            log.error("Couldn't save Product Category!");
            throw new ProductDomainException("Cloud not save Product!");
        }
        log.info("Product is saved with id : {}", productCategoryResult.getId().getValue());
        return productCategoryResult;
    }

    public void deleteProduct(Product product, Boolean forceDelete) {
        try {
            if (forceDelete != null && forceDelete) {
                productRepository.hardDelete(product);
            } else {
                productRepository.softDelete(product);
            }
        } catch (Exception e) {
            throw new ProductDomainException("Cloud not delete Product!");
        }
        log.info("Product is deleted with id : {}", product.getId().getValue());
    }
}
