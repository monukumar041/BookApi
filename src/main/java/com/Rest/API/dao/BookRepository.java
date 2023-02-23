package com.Rest.API.dao;
import com.Rest.API.Entitiy.Book;
import org.springframework.data.repository.CrudRepository;
public interface BookRepository extends CrudRepository<Book,Integer> {
    Book findById(int id);
    Book deleteById(int id);

}
