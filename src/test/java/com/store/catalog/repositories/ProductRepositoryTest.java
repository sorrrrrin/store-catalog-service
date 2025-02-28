package com.store.catalog.repositories;

import com.store.catalog.entities.Product;
import com.store.catalog.utils.TestConstants;
import com.store.catalog.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        Product product1 = new Product();
        product1.setPrice(100.0);
        product1.setSku(TestConstants.PRODUCT_SKU);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setPrice(200.0);
        product1.setSku(TestConstants.PRODUCT_SKU);
        productRepository.save(product2);

        Product product3 = new Product();
        product3.setPrice(150.0);
        product1.setSku(TestConstants.PRODUCT_SKU);
        productRepository.save(product3);
    }

    @Test
    void findBySkuAndPriceRangeTest() {
        List<Product> products = productRepository.findBySkuAndPriceRange(TestConstants.PRODUCT_SKU, 50.0, 150.0);
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getSku()).isEqualTo(TestConstants.PRODUCT_SKU);
        assertThat(products.get(0).getPrice()).isEqualTo(100.0);
    }
}