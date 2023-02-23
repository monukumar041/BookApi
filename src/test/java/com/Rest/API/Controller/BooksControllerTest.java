package com.Rest.API.Controller;

import com.Rest.API.BookServices.BookServices;
import com.Rest.API.BookServices.ProductService;
import com.Rest.API.Entitiy.Author;
import com.Rest.API.Entitiy.Book;
import com.Rest.API.dao.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;


import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BooksControllerTest {
    Book book1=new Book(1,"Python Complete Reference",new Author(1,"Monu","Kumar","English"));
    Book book2=new Book(2,"C++ Complete Reference",new Author(2,"Rohit","Kumar","Bangali"));
    Book book3=new Book(3,"Python Complete Reference",new Author(3,"Mohit","Kumar","Hindi"));
    Book book4=new Book(4,"Python Complete Reference",new Author(4,"sumit","Kumar","Urdu"));
    ObjectMapper objectMapper =new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    private MockMvc mockMvc;
    @Mock
    private BookServices bookServices;
    @InjectMocks
    private BookController booksController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(booksController).build();

    }

    @Test
    void getBooks_success() throws Exception {
        List<Book>bookList=new ArrayList<>(Arrays.asList(book1,book2,book3,book4));
        given(bookServices.getAllBooks()).willReturn(bookList);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/books")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(4)))
                .andExpect(jsonPath("$[2].title",is("Python Complete Reference")));
    }

    @Test
    void getBook_success() throws Exception{
        given(bookServices.getBookById(book1.getId())).willReturn(book1);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.title",is("Python Complete Reference")));

    }

    @Test
    void addBook_success() throws Exception {
        Book record=new Book(5,"React Complete Reference",new Author(5,"Farhan","Akhter","Kolkata"));
        given(bookServices.addBook(any(Book.class))).willAnswer(invocation -> invocation.getArgument(0));
        System.out.println(record);

        String content=objectWriter.writeValueAsString(record);

        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.title",is("React Complete Reference")));



    }

    @Test
    void deleteBook_success() throws Exception {
        given(bookServices.deleteBook(book1.getId())).willReturn(book1);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

//    @Test
//    void updateBook() {
//        Book book=new Book();
//        Author author=new Author();
//        author.setAuthor_id(312);
//        author.setFirstName("rahul");
//        author.setLastName("mallik");
//        author.setLanguage("Bengali");
//        book.setId(123);
//        book.setTitle("fewhbfhjwebfhjewbhf");
//        book.setAuthor(author);
//        booksController.UpdateBook(book,90);
//        verify(bookServices).updateBook(book,90);
//    }
}