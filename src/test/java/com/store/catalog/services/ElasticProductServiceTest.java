package com.store.catalog.services;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.dtos.elastic.ElasticProductDto;
import com.store.catalog.mappers.ElasticMapper;
import com.store.catalog.repositories.ElasticProductRepository;
import com.store.catalog.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ElasticProductServiceTest {

    @Mock
    private ElasticProductRepository elasticProductRepository;

    @Mock
    private ElasticMapper elasticMapper;

    @InjectMocks
    private ElasticProductService elasticProductService;

    private ProductDto productDto;
    private ElasticProductDto elasticProductDto;

    @BeforeEach
    void setUp() {
        productDto = TestUtils.getProductDto();
        elasticProductDto = TestUtils.getElasticProductDto();
    }

    @Test
    void getAllProductsTest() {
        when(elasticProductRepository.findAll()).thenReturn(Collections.singletonList(elasticProductDto));
        when(elasticMapper.elasticProductDtoToProductDto(elasticProductDto)).thenReturn(productDto);

        List<ProductDto> products = elasticProductService.getAllProducts();

        assertEquals(1, products.size());
        assertEquals(productDto, products.get(0));
    }

    @Test
    void addProductTest() {
        when(elasticMapper.productDtoToElasticProductDto(productDto)).thenReturn(elasticProductDto);

        elasticProductService.addProduct(productDto);

        verify(elasticProductRepository).save(elasticProductDto);
    }

    @Test
    void deleteAllProductsTest() {
        elasticProductService.deleteAllProducts();

        verify(elasticProductRepository).deleteAll();
    }
}