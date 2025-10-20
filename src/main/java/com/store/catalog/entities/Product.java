package com.store.catalog.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String sku;

    @Column(nullable = false)
    @Builder.Default
    private double ratings = 0.0;

    @Column(nullable = false)
    @Builder.Default
    private int reviews = 0;

    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
