package com.Rest.API.Controller;
import com.Rest.API.BookServices.ProductService;
import com.Rest.API.Entitiy.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/public/products")
    public List<Product>getProducts()
    {
        List<Product>products=productService.getProducts();
        return  products;
    }

    @GetMapping("/public/products/{prodId}")
    public Product getProduct(@PathVariable("prodId")int prodId)
    {
        Product product=productService.getProduct(prodId);
        return product;
    }

    @PostMapping("/admin/products")
    public Product addProduct(@RequestBody Product product)
    {
        Product product1=productService.addProduct(product);
        return product1;
    }

    @DeleteMapping("/admin/products/delete/{prodId}")
    public Product deleteProduct(@PathVariable("prodId")int prodId)
    {
        Product product=productService.deleteProduct(prodId);
        return product;
    }

    @PutMapping("/admin/products/update/{prodId}")
    public Product updateProduct(@PathVariable("prodId")int prodId,@RequestBody Product product)
    {
        Product product1=productService.updateProduct(product,prodId);
        return product1;
    }

}
