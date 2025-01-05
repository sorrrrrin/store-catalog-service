package com.store.catalog.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/catalog")
public class CatalogController {

    @GetMapping("/items")
    public String getItems() {
        return "List of catalog items";
    }

    @PostMapping("/items")
    public String addItem(@RequestBody String newItem) {
        // Logic to add the new item to the catalog
        return "Item added: " + newItem;
    }
}