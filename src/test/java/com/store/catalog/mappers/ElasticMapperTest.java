package com.store.catalog.mappers;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.dtos.elastic.ElasticProductDto;
import com.store.catalog.utils.TestConstants;
import com.store.catalog.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ElasticMapperTest {

    private final ElasticMapper elasticMapper = new ElasticMapperImpl();

    @Test
    void productDtoToElasticProductDtoTest() {
        ProductDto productDto = TestUtils.getProductDto();

        ElasticProductDto elasticProductDto = elasticMapper.productDtoToElasticProductDto(productDto);

        assertNotNull(elasticProductDto);
        assertEquals(TestConstants.PRODUCT_ID, elasticProductDto.getId());
        assertEquals(TestConstants.PRODUCT_SKU, elasticProductDto.getSku());
    }

    @Test
    void productDtoToElasticProductDto_NullProductDto_ReturnsNullTest() {
        ElasticProductDto elasticProductDto = elasticMapper.productDtoToElasticProductDto(null);
        assertNull(elasticProductDto);
    }

    @Test
    void elasticProductDtoToProductDtoTest() {
        ElasticProductDto elasticProductDto = TestUtils.getElasticProductDto();

        ProductDto productDto = elasticMapper.elasticProductDtoToProductDto(elasticProductDto);

        assertNotNull(productDto);
        assertEquals(TestConstants.PRODUCT_ID, productDto.getId());
        assertEquals(TestConstants.PRODUCT_SKU, productDto.getSku());
    }

    @Test
    void elasticProductDtoToProductDto_NullElasticProductDto_ReturnsNullTest() {
        ProductDto productDto = elasticMapper.elasticProductDtoToProductDto(null);
        assertNull(productDto);
    }
}