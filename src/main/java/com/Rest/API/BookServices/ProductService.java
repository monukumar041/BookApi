package com.Rest.API.BookServices;
import com.Rest.API.Entitiy.Product;
import com.Rest.API.dao.ProductRepository;
import com.Rest.API.exception.NoDataFoundException;
import com.Rest.API.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        List<Product>list=(List<Product>) productRepository.findAll();
        return list;
//        if(list.size()>0)
//        {
//            return list;
//        }
//        throw new NoDataFoundException("No Product Data found in DataBase");
    }

    public Product getProduct(int prodId) {
        Product prod=null;
        try
        {
            prod=productRepository.getProductById(prodId);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return prod;
//        if(prod!=null)
//        {
//            return prod;
//        }
//        throw  new ResourceNotFoundException("The Product is not found with id:"+prodId);
    }

    public Product addProduct(Product prod) {
        Product p=productRepository.save(prod);
        return p;
    }

    public Product deleteProduct(int prodId) {
        Product p=productRepository.deleteById(prodId);
        return p;
    }

    public Product updateProduct(Product p, int prodId) {
        p.setId(prodId);
        Product pro=productRepository.save(p);
        return pro;
    }
}
