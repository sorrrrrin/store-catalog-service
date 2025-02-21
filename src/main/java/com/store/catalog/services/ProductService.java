package com.store.catalog.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.catalog.commons.Constants;
import com.store.catalog.commons.kafka.events.ProductUpdateEvent;
import com.store.catalog.dtos.ProductDto;
import com.store.catalog.entities.Product;
import com.store.catalog.exceptions.ProductNotFoundException;
import com.store.catalog.mappers.CatalogMapper;
import com.store.catalog.mappers.ElasticMapper;
import com.store.catalog.repositories.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    @Value("${spring.kafka.topic}")
    private String topic;

    @Autowired
    private CatalogMapper catalogMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    ElasticProductService elasticProductService;

    @Autowired
    private ElasticMapper elasticMapper;

    public List<ProductDto> getAllProducts() {
        log.debug("Getting all products");
        //test elastic search
        elasticProductService.getAllProducts().forEach(System.out::println);

        return productRepository.findAll().stream().map(catalogMapper::productToProductDto).collect(Collectors.toList());
    }

    public ProductDto getProductById(String id) {
        return catalogMapper.productToProductDto(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found")));
    }

    public ProductDto addProduct(ProductDto productDto) {
        return catalogMapper.productToProductDto(productRepository.save(catalogMapper.productDtoToProduct(productDto)));
    }

    public ProductDto updateProduct(String id, ProductDto productDto) throws JsonProcessingException {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id " + id + " not found"));

        existingProduct.setName(productDto.getName());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setQuantity(productDto.getQuantity());
        existingProduct.setSku(productDto.getSku());

        ObjectMapper objectMapper = new ObjectMapper();
        ProductUpdateEvent productUpdateEvent = new ProductUpdateEvent(Constants.PRODUCT_UPDATED, productDto.getId(), productDto.getName(), productDto.getDescription(), productDto.getSku());

        kafkaTemplate.send(topic,  objectMapper.writeValueAsString(productUpdateEvent));

        return catalogMapper.productToProductDto(productRepository.save(existingProduct));
    }

    public void deleteProduct(ProductDto productDto) {
        productRepository.delete(catalogMapper.productDtoToProduct(productDto));
    }

    public void deleteAllProducts() {
        productRepository.deleteAll();
    }
}
