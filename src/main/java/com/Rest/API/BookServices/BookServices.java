package com.Rest.API.BookServices;

import com.Rest.API.Entitiy.Book;
import com.Rest.API.dao.BookRepository;
import com.Rest.API.exception.NoDataFoundException;
import com.Rest.API.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class BookServices {
    @Autowired
    BookRepository bookRepository;

    public BookServices(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks()
    {
        List<Book>list=(List<Book>) bookRepository.findAll();
        return list;
//        if(list.size()>0)
//        {
//            return list;
//        }
//        throw new NoDataFoundException("No Book found in the Database");
    }

    public Book getBookById(int id)
    {
       Book book=bookRepository.findById(id);
       if(book!=null)
       {
           return book;
       }
       else {
           throw new ResourceNotFoundException("No book found with Book Id:"+id);
       }

    }

    public Book addBook(Book b)
    {
        System.out.println(b);
        Book results=bookRepository.save(b);
        return results;
    }

    public Book deleteBook(int Bid)
    {

        Book b=this.bookRepository.deleteById(Bid);
        return b;
    }

    public Book updateBook(Book book,int bookId)
    {
        book.setId(bookId);
        Book b=bookRepository.save(book);
        return b;
    }
}
