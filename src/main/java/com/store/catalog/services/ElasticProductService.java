package com.store.catalog.services;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.dtos.elastic.ElasticProductDto;
import com.store.catalog.mappers.ElasticMapper;
import com.store.catalog.repositories.ElasticProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ElasticProductService {
    @Autowired
    private ElasticProductRepository elasticProductRepository;

    @Autowired
    private ElasticMapper elasticMapper;

    public void saveProduct(ProductDto productDto) {
        log.debug("Saving product to ElasticSearch");
        elasticProductRepository.save(elasticMapper.productDtoToElasticProductDto(productDto));
    }
}
