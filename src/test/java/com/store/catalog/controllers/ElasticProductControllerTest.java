package com.store.catalog.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.catalog.config.TestSecurityConfig;
import com.store.catalog.dtos.ProductDto;
import com.store.catalog.services.ElasticProductService;
import com.store.catalog.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ElasticProductController.class)
@Import(TestSecurityConfig.class)
@ActiveProfiles("elastic-enabled")
public class ElasticProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private ElasticProductService elasticProductService;

    private ProductDto productDto;

    @BeforeEach
    void setUp() {
        productDto = TestUtils.getProductDto();
    }

    @Test
    void getElasticProductsTest() throws Exception {
        List<ProductDto> products = Collections.singletonList(productDto);
        when(elasticProductService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/catalog/elastic-products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(productDto.getId()));
    }
}