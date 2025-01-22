package com.store.catalog.services;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.dtos.elastic.ElasticProductDto;
import com.store.catalog.repositories.ElasticProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElasticProductService {
    @Autowired
    private ElasticProductRepository elasticProductRepository;

    public void saveProduct(ProductDto productDto) {
        ElasticProductDto elasticProductDto = ElasticProductDto.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .sku(productDto.getSku())
                .build();

        elasticProductRepository.save(elasticProductDto);
    }
}
