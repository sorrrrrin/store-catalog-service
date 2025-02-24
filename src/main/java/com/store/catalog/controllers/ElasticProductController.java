package com.store.catalog.controllers;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.services.ElasticProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class ElasticProductController {
    @Autowired
    private ElasticProductService elasticProductService;

    @GetMapping("/elastic-products")
    public ResponseEntity<List<ProductDto>> getElasticProducts() {
        List<ProductDto> allProducts = elasticProductService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }
}