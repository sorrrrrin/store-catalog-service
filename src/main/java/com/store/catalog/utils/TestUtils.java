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
                .ratings(4.5)
                .reviews(10)
                .image("http://example.com/img.jpg")
                .build();
    }

    public static ProductDto getProductDto() {
        return ProductDto.builder()
                .id(TestConstants.PRODUCT_ID)
                .sku(TestConstants.PRODUCT_SKU)
                .ratings(4.5)
                .reviews(10)
                .image("http://example.com/img.jpg")
                .build();
    }

    public static ElasticProductDto getElasticProductDto() {
        return ElasticProductDto.builder()
                .id(TestConstants.PRODUCT_ID)
                .sku(TestConstants.PRODUCT_SKU)
                .ratings(4.5)
                .reviews(10)
                .image("http://example.com/img.jpg")
                .build();
    }
}
