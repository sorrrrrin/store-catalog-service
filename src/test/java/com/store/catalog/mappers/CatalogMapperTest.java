package com.store.catalog.mappers;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.entities.Product;
import com.store.catalog.utils.TestConstants;
import com.store.catalog.utils.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class CatalogMapperTest {

    private final CatalogMapper catalogMapper = new CatalogMapperImpl();

    @Test
    void productToProductDtoTest() {
        Product product = TestUtils.getProduct();

        ProductDto productDto = catalogMapper.productToProductDto(product);

        assertNotNull(productDto);
        assertEquals(TestConstants.PRODUCT_ID, productDto.getId());
        assertEquals(TestConstants.PRODUCT_SKU, productDto.getSku());
        assertEquals(product.getPrice(), productDto.getPrice());
    }

    @Test
    void productToProductDto_NullProduct_ReturnsNullTest() {
        ProductDto productDto = catalogMapper.productToProductDto(null);
        assertNull(productDto);
    }

    @Test
    void productDtoToProductTest() {
        ProductDto productDto = TestUtils.getProductDto();

        Product product = catalogMapper.productDtoToProduct(productDto);

        assertNotNull(product);
        assertEquals(TestConstants.PRODUCT_ID, product.getId());
        assertEquals(TestConstants.PRODUCT_SKU, product.getSku());
        assertEquals(productDto.getPrice(), product.getPrice());
    }

    @Test
    void productDtoToProduct_NullProductDto_ReturnsNullTest() {
        Product product = catalogMapper.productDtoToProduct(null);
        assertNull(product);
    }
}