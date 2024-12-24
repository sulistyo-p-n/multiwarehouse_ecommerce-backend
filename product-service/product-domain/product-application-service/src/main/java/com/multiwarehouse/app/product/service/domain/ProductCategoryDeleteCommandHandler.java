package com.multiwarehouse.app.product.service.domain;

import com.multiwarehouse.app.domain.valueobject.ProductCategoryId;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCategoryCommand;
import com.multiwarehouse.app.product.service.domain.dto.delete.DeleteProductCategoryResponse;
import com.multiwarehouse.app.product.service.domain.entity.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class ProductCategoryDeleteCommandHandler {

    private final ProductCategoryHelper productCategoryHelper;

    public ProductCategoryDeleteCommandHandler(ProductCategoryHelper productCategoryHelper) {
        this.productCategoryHelper = productCategoryHelper;
    }

    @Transactional
    public DeleteProductCategoryResponse deleteProductCategory(DeleteProductCategoryCommand deleteProductCategoryCommand) {
        ProductCategoryId productCategoryId = new ProductCategoryId(deleteProductCategoryCommand.getId());
        ProductCategory productCategory = productCategoryHelper.findProductCategoryById(productCategoryId);
        productCategoryHelper.deleteProductCategory(productCategory, deleteProductCategoryCommand.getForceDelete());
        log.info("Product category is deleted with id: {}", productCategoryId.getValue());
        return DeleteProductCategoryResponse.builder().id(productCategoryId.getValue()).build();
    }
}
