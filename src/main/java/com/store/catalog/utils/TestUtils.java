package com.store.catalog.utils;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.dtos.elastic.ElasticProductDto;
import com.store.catalog.entities.Product;

public class TestUtils {
    public static Product getProduct() {
        return Product.builder()
                .id(TestConstants.PRODUCT_ID)
                .sku(TestConstants.PRODUCT_SKU)
                .price(1)
                .build();
    }

    public static ProductDto getProductDto() {
        return ProductDto.builder()
                .id(TestConstants.PRODUCT_ID)
                .sku(TestConstants.PRODUCT_SKU)
                .build();
    }

    public static ElasticProductDto getElasticProductDto() {
        return ElasticProductDto.builder()
                .id(TestConstants.PRODUCT_ID)
                .sku(TestConstants.PRODUCT_SKU)
                .build();
    }
}
