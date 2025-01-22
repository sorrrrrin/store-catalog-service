package com.store.catalog.dtos.elastic;

import com.store.catalog.dtos.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElasticProductDto {
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String sku;
    private CategoryDto category;
}