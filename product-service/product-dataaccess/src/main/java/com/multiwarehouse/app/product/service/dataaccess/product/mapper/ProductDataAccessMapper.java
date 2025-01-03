package com.multiwarehouse.app.product.service.dataaccess.product.mapper;

import com.multiwarehouse.app.domain.valueobject.Money;
import com.multiwarehouse.app.domain.valueobject.ProductId;
import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductEntity;
import com.multiwarehouse.app.product.service.dataaccess.product.entity.ProductImageEntity;
import com.multiwarehouse.app.product.service.domain.entity.Product;
import com.multiwarehouse.app.product.service.domain.entity.ProductImage;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductDataAccessMapper {

    private final ProductCategoryDataAccessMapper productCategoryDataAccessMapper;
    private final ProductImageDataAccessMapper productImageDataAccessMapper;

    public ProductDataAccessMapper(ProductCategoryDataAccessMapper productCategoryDataAccessMapper, ProductImageDataAccessMapper productImageDataAccessMapper) {
        this.productCategoryDataAccessMapper = productCategoryDataAccessMapper;
        this.productImageDataAccessMapper = productImageDataAccessMapper;
    }

    public Product productEntityToProduct(ProductEntity productEntity) {
        ProductId productId = new ProductId(productEntity.getId());
        Product product = Product.builder()
                .withId(productId)
                .withCode(productEntity.getCode())
                .withName(productEntity.getName())
                .withDescription(productEntity.getDescription())
                .withPrice(new Money(productEntity.getPrice()))
                .withCategory(productCategoryDataAccessMapper.productCategoryEntityToProductCategory(productEntity.getCategory()))
                .withImages(productImageEntitiesToProductImages(productEntity.getImages()))
                .withActive(productEntity.getActive())
                .withSoftDeleted(productEntity.isSoftDeleted())
                .build();
        product.getImages().forEach(productImage -> productImage.setProductId(productId));
        return product;
    }

    private List<ProductImage> productImageEntitiesToProductImages(List<ProductImageEntity> productImageEntities) {
        return productImageEntities.stream()
                .map(productImageDataAccessMapper::productImageEntityToProductImage)
                .collect(Collectors.toList());
    }

    public ProductEntity productToProductEntity(Product product) {
        ProductEntity productEntity = ProductEntity.builder()
                .id(product.getId().getValue())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice().getAmount())
                .category(productCategoryDataAccessMapper.productCategoryToProductCategoryEntity(product.getCategory()))
                .images(productImageToProductImageEntities(product.getImages()))
                .active(product.isActive())
                .build();
        productEntity.getImages().forEach(productImageEntity -> productImageEntity.setProduct(productEntity));
        return productEntity;
    }

    private List<ProductImageEntity> productImageToProductImageEntities(List<ProductImage> productImages) {
        return productImages.stream()
                .map(productImageDataAccessMapper::productImageToProductImageEntity)
                .collect(Collectors.toList());
    }
}
