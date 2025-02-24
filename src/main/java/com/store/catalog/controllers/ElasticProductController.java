package com.store.catalog.controllers;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.services.ElasticProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class ElasticProductController {
    @Autowired
    private ElasticProductService elasticProductService;

    @Operation(summary = "Get all elastic products", description = "Retrieve a list of all products from Elasticsearch")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully")
    })
    @GetMapping("/elastic-products")
    public ResponseEntity<List<ProductDto>> getElasticProducts() {
        List<ProductDto> allProducts = elasticProductService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }
}