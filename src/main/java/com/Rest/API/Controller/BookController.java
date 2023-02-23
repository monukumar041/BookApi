package com.Rest.API.Controller;

import com.Rest.API.BookServices.BookServices;
import com.Rest.API.Entitiy.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class BookController {
    @Autowired
    BookServices bookServices;
    @GetMapping("/public/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> list = bookServices.getAllBooks();
        if (list.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(list));
    }


    @GetMapping("/public/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
        Book book = bookServices.getBookById(id);
        if (book == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(book));
    }
    @PostMapping("/admin/books/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book b=bookServices.addBook(book);
        if(b!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    @DeleteMapping("/admin/books/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable("id") int BookId) {
        Book b=bookServices.deleteBook(BookId);
        if(b!=null)
        {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping("/admin/books/update/{id}")
    public ResponseEntity<Void> UpdateBook(@RequestBody Book book, @PathVariable("id") int bookId) {
        try {
            this.bookServices.updateBook(book, bookId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
}
