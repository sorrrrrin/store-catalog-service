package com.store.catalog.services;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.mappers.ElasticMapper;
import com.store.catalog.repositories.ElasticProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Profile("elastic-enabled")
@Slf4j
public class ElasticProductService {
    private final ElasticProductRepository elasticProductRepository;

    private final ElasticMapper elasticMapper;

    @Autowired
    public ElasticProductService(ElasticProductRepository elasticProductRepository, ElasticMapper elasticMapper) {
        this.elasticProductRepository = elasticProductRepository;
        this.elasticMapper = elasticMapper;
    }

    public List<ProductDto> getAllProducts() {
        log.debug("Getting all products");
        return StreamSupport.stream(elasticProductRepository.findAll().spliterator(), false)
                .map(elasticMapper::elasticProductDtoToProductDto).collect(Collectors.toList());
    }

    public void addProduct(ProductDto productDto) {
        log.debug("Saving product to ElasticSearch");
        elasticProductRepository.save(elasticMapper.productDtoToElasticProductDto(productDto));
    }

    public void deleteAllProducts() {
        elasticProductRepository.deleteAll();
    }
}
