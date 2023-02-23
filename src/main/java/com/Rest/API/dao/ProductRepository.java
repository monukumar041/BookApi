package com.Rest.API.dao;
import com.Rest.API.Entitiy.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {
    Product getProductById(int id);
    Product deleteById(int id);
}
