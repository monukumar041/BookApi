package com.Rest.API.Controller;
import com.Rest.API.BookServices.ProductService;
import com.Rest.API.Entitiy.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class ProductControllerTest {

    @Mock
    private ProductService productService;
    @InjectMocks
    private ProductController productController;
    private MockMvc mockMvc;

    Product product1=new Product(1,"Rice","Ranchi");
    Product product2=new Product(2,"Daal","Bokaro");
    Product product3=new Product(3,"Busicut","Delhi");
    ObjectMapper objectMapper =new ObjectMapper();
    ObjectWriter objectWriter=objectMapper.writer();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc= MockMvcBuilders.standaloneSetup(productController).build();

    }

    @Test
    void getProducts_success()throws Exception {
        List<Product>productList=new ArrayList<>(Arrays.asList(product1,product2,product3));
        given(productService.getProducts()).willReturn(productList);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(jsonPath("$[2].prodName",is("Busicut")));
    }

    @Test
    void getProduct_success() throws Exception{
        given(productService.getProduct(product1.getId())).willReturn(product1);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.prodName",is("Rice")));
    }

    @Test
    void addProduct_success() throws Exception {
        Product product=new Product(4,"Milk","Bangaluru");
        given(productService.addProduct(any(Product.class))).willAnswer(invocation -> invocation.getArgument(0));

        String content=objectWriter.writeValueAsString(product);

        MockHttpServletRequestBuilder mockRequest=MockMvcRequestBuilders.post("/products")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",notNullValue()))
                .andExpect(jsonPath("$.prodName",is("Milk")));


    }

    @Test
    void deleteProduct_success() throws Exception {
        given(productService.deleteProduct(product1.getId())).willReturn(product1);
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

//    @Test
//    void updateProduct() {
//        Product product=new Product();
//        product.setProdName("rice");
//        product.setAddress("Bokaro");
//        productController.updateProduct(product,45);
//        verify(productService).updateProduct(product,45);
//    }
}