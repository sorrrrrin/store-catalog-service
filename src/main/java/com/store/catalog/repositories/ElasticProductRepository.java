package com.store.catalog.repositories;

import com.store.catalog.dtos.elastic.ElasticProductDto;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElasticProductRepository extends ElasticsearchRepository<ElasticProductDto, String> {
    List<ElasticProductDto> findByName(String name);
}