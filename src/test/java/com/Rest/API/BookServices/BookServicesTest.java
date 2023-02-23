package com.Rest.API.BookServices;
import com.Rest.API.Entitiy.Author;
import com.Rest.API.Entitiy.Book;
import com.Rest.API.dao.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class BookServicesTest {

    Book book1=new Book(1,"Python Complete Reference",new Author(1,"Monu","Kumar","English"));
    Book book2=new Book(2,"C++ Complete Reference",new Author(2,"Rohit","Kumar","Bangali"));
    Book book3=new Book(3,"Python Complete Reference",new Author(3,"Mohit","Kumar","Hindi"));
    Book book4=new Book(4,"Python Complete Reference",new Author(4,"sumit","Kumar","Urdu"));

    @InjectMocks
    private BookServices bookServices;
    @Mock
    private BookRepository bookRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks_success() throws Exception {
        List<Book>bookList=new ArrayList<>(Arrays.asList(book1,book2,book3,book4));
        given(bookRepository.findAll()).willReturn(bookList);
        List<Book>books=bookServices.getAllBooks();
        assertEquals(4,books.size());
        verify(bookRepository).findAll();
    }

    @Test
    void getBookById_success() throws Exception {
        given(bookRepository.findById(book1.getId())).willReturn(book1);
        Book b=bookServices.getBookById(book1.getId());
        assertEquals("Python Complete Reference",b.getTitle());
        assertEquals("Monu",b.author.getFirstName());
        verify(bookRepository).findById(book1.getId());
    }

    @Test
    void addBook_success() throws Exception {
        Book b=new Book(4,"C# Complete Reference",new Author(4,"Nile","Tiwari","Hindi"));
        given(bookRepository.save(b)).willReturn(b);
        Book book=bookServices.addBook(b);
        assertEquals("Nile",b.author.getFirstName());
        verify(bookRepository).save(b);
    }

    @Test
    void deleteBook() {
        given(bookRepository.deleteById(book1.getId())).willReturn(book1);
        Book book=bookServices.deleteBook(book1.getId());
        assertEquals("Python Complete Reference",book.getTitle());
    }

    @Test
    void updateBook() {
        Book updatedBook=new Book(1,"Java Complete Reference",new Author(1,"komal","sahu","English"));
        given(bookRepository.save(updatedBook)).willReturn(updatedBook);
        Book b=bookServices.updateBook(updatedBook,book1.getId());
        assertEquals("komal",b.author.getFirstName());

    }
}