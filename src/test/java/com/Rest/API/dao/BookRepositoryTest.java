package com.Rest.API.dao;
import com.Rest.API.Entitiy.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;
    @Test
    void findById_success() throws Exception{
        Book book=bookRepository.findById(302);
        assertEquals("PythonComplete Reference",book.getTitle());



    }
}