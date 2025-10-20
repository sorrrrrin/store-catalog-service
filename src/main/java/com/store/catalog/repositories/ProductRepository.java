package com.store.catalog.repositories;

import com.store.catalog.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p WHERE p.sku = :sku AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> findBySkuAndPriceRange(@Param("sku") String sku, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE Product p SET p.ratings = ((COALESCE(p.ratings, 0.0) * COALESCE(p.reviews, 0)) + :rating) / (COALESCE(p.reviews, 0) + 1), p.reviews = COALESCE(p.reviews, 0) + 1 WHERE p.id = :id")
    int updateRatingsAndIncrementReviews(@Param("id") String id, @Param("rating") double rating);
}
