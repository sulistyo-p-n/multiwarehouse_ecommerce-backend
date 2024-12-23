package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.create.CreateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoriesCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoriesResponse;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.get.GetProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.update.UpdateProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import com.multiwarehouse.app.product.service.domain.exception.ProductCategoryNotFoundException;
import com.multiwarehouse.app.product.service.domain.mapper.ProductCategoryDataMapper;
import com.multiwarehouse.app.product.service.domain.ports.output.repository.ProductCategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class ProductCategoryDeleteCommandHandler {

    private final ProductCategoryDataMapper productCategoryDataMapper;
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryDeleteCommandHandler(ProductCategoryDataMapper productCategoryDataMapper, ProductCategoryRepository productCategoryRepository) {
        this.productCategoryDataMapper = productCategoryDataMapper;
        this.productCategoryRepository = productCategoryRepository;
    }

    @Transactional
    public DeleteProductCategoryResponse deleteProductCategory(DeleteProductCategoryCommand deleteProductCategoryCommand) {
        ProductCategoryId productCategoryId = new ProductCategoryId(deleteProductCategoryCommand.getId());
        Optional<ProductCategory> productCategory = productCategoryRepository.findById(productCategoryId);
        if (productCategory.isEmpty()) {
            log.warn("Couldn't find product category with id: {} ", deleteProductCategoryCommand.getId());
            throw new ProductCategoryNotFoundException("Couldn't find product category with id: {} " +
                    deleteProductCategoryCommand.getId());
        }
        productCategoryRepository.
    }
}
