package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoriesCommand;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.exception.ProductCategoryDomainException;
import com.multiwarehouse.app.product.service.domain.exception.ProductCategoryNotFoundException;
import com.multiwarehouse.app.product.service.domain.ports.output.repository.ProductCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class ProductCategoryHelper {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryHelper(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<ProductCategory> findProductCategories() {
        return productCategoryRepository.findAll();
    }

    public List<ProductCategory> findProductCategories(GetProductCategoriesCommand getProductCategoriesCommand) {
        return productCategoryRepository.findByCriteria(
                getProductCategoriesCommand.getWithInactive(),
                getProductCategoriesCommand.getWithTrashed(),
                getProductCategoriesCommand.getSearch());
    }

    public ProductCategory findProductCategoryById(ProductCategoryId productCategoryId) {
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productCategoryId);
        if (productCategory.isEmpty()) {
            log.warn("Couldn't find Product Category with id: {} ", productCategoryId.getValue());
            throw new ProductCategoryNotFoundException("Couldn't find Product Category with id: " + productCategoryId.getValue());
        }
        return productCategory.get();
    }

    public ProductCategory saveProductCategory(ProductCategory productCategory) {
        ProductCategory productCategoryResult = productCategoryRepository.save(productCategory);
        if (productCategoryResult == null) {
            log.error("Couldn't save Product Category!");
            throw new ProductCategoryDomainException("Cloud not save product category!");
        }
        log.info("Product Category is saved with id : {}", productCategoryResult.getId().getValue());
        return productCategoryResult;
    }

    public void deleteProductCategory(ProductCategory productCategory, Boolean forceDelete) {
        try {
            log.info("Product Category is try delete with id : {} {}", productCategory.getId().getValue(), forceDelete);
            if (forceDelete != null && forceDelete) {
                productCategoryRepository.hardDelete(productCategory);
            } else {
                productCategoryRepository.softDelete(productCategory);
            }
        } catch (Exception e) {
            throw new ProductCategoryDomainException("Cloud not delete Product Category!");
        }
        log.info("Product Category is deleted with id : {}", productCategory.getId().getValue());
    }
}
