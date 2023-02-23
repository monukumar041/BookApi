package com.Rest.API.BookServices;

import com.Rest.API.Entitiy.Product;
import com.Rest.API.dao.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    Product product1=new Product(1,"Rice","Ranchi");
    Product product2=new Product(2,"Daal","Delhi");
    Product product3=new Product(3,"Buscuit","Kolkata");
    Product product4=new Product(4,"Paste","Mumbai");

    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProducts_Success() throws Exception {
        List<Product>productList= new ArrayList<>(Arrays.asList(product1,product2,product3,product4));
        given(productRepository.findAll()).willReturn(productList);
        List<Product>products=productService.getProducts();
        assertEquals(4,products.size());
        verify(productRepository).findAll();
    }

    @Test
    void getProduct_success() throws Exception {
        given(productRepository.getProductById(product1.getId())).willReturn(product1);
        Product product=productService.getProduct(product1.getId());
        assertEquals("Rice",product.getProdName());
        verify(productRepository).getProductById(product1.getId());
    }

    @Test
    void addProduct_success() throws Exception {
        Product record=new Product(13,"Watch","Ranchi");
        given(productRepository.save(record)).willReturn(record);
        Product product=productService.addProduct(record);
        assertEquals("Ranchi",product.getAddress());
        verify(productRepository).save(record);
    }

    @Test
    void deleteProduct_success() throws Exception {
        given(productRepository.deleteById(product1.getId())).willReturn(product1);
        Product p=productService.deleteProduct(product1.getId());
        assertEquals("Ranchi",p.getAddress());
        verify(productRepository).deleteById(product1.getId());
    }

    @Test
    void updateProduct() {
        Product product=new Product(1,"Brown Rice","bangaluru");
        given(productRepository.save(product)).willReturn(product);
        Product p=productService.updateProduct(product,product1.getId());
        assertEquals("Brown Rice",p.getProdName());
        verify(productRepository).save(product);
    }
}