package com.store.catalog.controllers;

import com.store.catalog.config.TestSecurityConfig;
import com.store.catalog.exceptions.ProductNotFoundException;
import com.store.catalog.services.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@Import(TestSecurityConfig.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void productNotFoundTest() throws Exception {
        UUID productId = UUID.randomUUID();
        Mockito.when(productService.getProductById(productId.toString()))
                .thenThrow(new ProductNotFoundException("Product with id " + productId + " not found"));

        mockMvc.perform(get("/api/catalog/products/" + productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Product with id " + productId + " not found")
                );
    }

    @Test
    void generalExceptionTest() throws Exception {
        UUID productId = UUID.randomUUID();
        Mockito.when(productService.getProductById(productId.toString()))
                .thenThrow(new RuntimeException("Unexpected error"));

        mockMvc.perform(get("/api/catalog/products/" + productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("An unexpected error occurred"));
    }
}
