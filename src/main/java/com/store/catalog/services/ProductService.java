package com.store.catalog.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.catalog.commons.Constants;
import com.store.catalog.commons.kafka.events.ProductUpdateEvent;
import com.store.catalog.dtos.ProductDto;
import com.store.catalog.dtos.elastic.ElasticProductDto;
import com.store.catalog.entities.Product;
import com.store.catalog.mappers.ProductMapper;
import com.store.catalog.repositories.ElasticProductRepository;
import com.store.catalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Value("${spring.kafka.topic}")
    private String topic;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ElasticProductRepository elasticProductRepository;


    public List<ProductDto> getAllProducts() {
        elasticProductRepository.findAll().forEach(elasticProductDto -> {
            ProductDto productDto = ProductDto.builder()
                    .id(elasticProductDto.getId())
                    .name(elasticProductDto.getName())
                    .description(elasticProductDto.getDescription())
                    .price(elasticProductDto.getPrice())
                    .quantity(elasticProductDto.getQuantity())
                    .sku(elasticProductDto.getSku())
                    .build();
            System.out.println(productDto.getName());
        });

        return productRepository.findAll().stream().map(productMapper::productToProductDto).collect(Collectors.toList());
    }

    public ProductDto getProductById(String id) {
        return productMapper.productToProductDto(productRepository.findById(id).orElse(null));
    }

    public ProductDto addProduct(ProductDto productDto) {
        ElasticProductDto elasticProductDto = ElasticProductDto.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .sku(productDto.getSku())
                .build();

        elasticProductRepository.save(elasticProductDto);
        return productMapper.productToProductDto(productRepository.save(productMapper.productDtoToProduct(productDto)));
    }

    public ProductDto updateProduct(String id, ProductDto productDto) throws JsonProcessingException {
        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct != null) {
            existingProduct.setName(productDto.getName());
            existingProduct.setDescription(productDto.getDescription());
            existingProduct.setPrice(productDto.getPrice());
            existingProduct.setQuantity(productDto.getQuantity());
            existingProduct.setSku(productDto.getSku());

            ObjectMapper objectMapper = new ObjectMapper();
            ProductUpdateEvent productUpdateEvent = new ProductUpdateEvent(Constants.PRODUCT_UPDATED, productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getSku());

            kafkaTemplate.send(topic,  objectMapper.writeValueAsString(productUpdateEvent));

            return productMapper.productToProductDto(productRepository.save(existingProduct));
        }
        return null;
    }

    public void deleteProduct(ProductDto productDto) {
        productRepository.delete(productMapper.productDtoToProduct(productDto));
    }

    public void deleteAllProducts() {
        elasticProductRepository.deleteAll();;
        productRepository.deleteAll();
    }


}
