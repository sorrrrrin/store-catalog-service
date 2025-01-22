package com.store.catalog.services;

import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import com.store.catalog.dtos.ProductDto;
import com.store.catalog.dtos.elastic.ElasticProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.elastic.clients.elasticsearch.ElasticsearchClient;


import java.io.IOException;

@Service
public class ElasticProductLowLevelService {
    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public void indexProduct(ProductDto productDto) {
        ElasticProductDto elasticProductDto = ElasticProductDto.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .sku(productDto.getSku())
                .build();
        try {
            // Check if the index exists
            boolean indexExists = elasticsearchClient.indices().exists(ExistsRequest.of(e -> e.index("products"))).value();

            // Create the index if it doesn't exist
            if (!indexExists) {
                CreateIndexRequest createIndexRequest = CreateIndexRequest.of(c -> c.index("products"));
                CreateIndexResponse createIndexResponse = elasticsearchClient.indices().create(createIndexRequest);
                // Handle the response if needed
            }

            IndexRequest<ElasticProductDto> request = IndexRequest.of(i -> i
                    .index("products")
                    .id(elasticProductDto.getId())
                    .document(elasticProductDto)
            );
            IndexResponse response = elasticsearchClient.index(request);
        } catch (IOException e) {
            // Handle exception (e.g., log and retry)
        }
    }
}
