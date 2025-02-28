package com.store.catalog.repositories;

import com.store.catalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE p.sku = :sku AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findBySkuAndPriceRange(@Param("sku") String sku, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
