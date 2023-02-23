package com.Rest.API.dao;
import com.Rest.API.Entitiy.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void getProductById_success() throws Exception {
        Product product=productRepository.getProductById(1);
        assertEquals("Daal",product.getProdName());
    }
}