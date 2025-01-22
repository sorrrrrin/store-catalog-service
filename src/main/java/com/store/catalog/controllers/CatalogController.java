package com.store.catalog.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.store.catalog.dtos.ProductDto;
import com.store.catalog.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> allProducts = productService.getAllProducts();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.addProduct(productDto);
        return ResponseEntity.ok(savedProduct);
    }

    @PutMapping("/products")
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto productDto) throws JsonProcessingException {
        ProductDto savedProduct = productService.updateProduct(productDto.getId(), productDto);
        return ResponseEntity.ok(savedProduct);
    }

    @DeleteMapping("/products")
    public ResponseEntity<ProductDto> deleteProduct(@RequestBody ProductDto productDto) {
        productService.deleteProduct(productDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products/all")
    public ResponseEntity<ProductDto> deleteAllProducts() {
        productService.deleteAllProducts();
        return ResponseEntity.ok().build();
    }
}