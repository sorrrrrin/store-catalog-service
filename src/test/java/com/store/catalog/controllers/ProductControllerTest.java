package com.store.catalog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.catalog.config.TestSecurityConfig;
import com.store.catalog.dtos.ProductDto;
import com.store.catalog.services.ProductService;
import com.store.catalog.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(ProductController.class)
@Import(TestSecurityConfig.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ProductService productService;

    ProductDto productDto;

    @BeforeEach
    void setUp() {
        productDto = TestUtils.getProductDto();
    }

    @Test
    void getProductsTest() throws Exception {
        List<ProductDto> products = Collections.singletonList(productDto);
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/catalog/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(productDto.getId()));
    }

    @Test
    void addProductTest() throws Exception {
        when(productService.addProduct(any(ProductDto.class))).thenReturn(productDto);

        String productDtoJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(post("/api/catalog/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productDto.getId()));
    }

    @Test
    void updateProductTest() throws Exception {
        when(productService.updateProduct(anyString(), any(ProductDto.class))).thenReturn(productDto);

        String productDtoJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(put("/api/catalog/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productDtoJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productDto.getId()));
    }

    @Test
    void deleteProductTest() throws Exception {
        doNothing().when(productService).deleteProduct(any(ProductDto.class));

        String productDtoJson = objectMapper.writeValueAsString(productDto);

        mockMvc.perform(delete("/api/catalog/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productDtoJson))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAllProductsTest() throws Exception {
        doNothing().when(productService).deleteAllProducts();

        mockMvc.perform(delete("/api/catalog/products/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getProductByIdTest() throws Exception {
        when(productService.getProductById(productDto.getId())).thenReturn(productDto);

        mockMvc.perform(get("/api/catalog/products/{id}", productDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(productDto.getId()));
    }
}