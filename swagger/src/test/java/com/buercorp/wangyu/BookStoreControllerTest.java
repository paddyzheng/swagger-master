package com.buercorp.wangyu;

import com.buercorp.wangyu.controller.BookStoreController;
import com.buercorp.wangyu.model.enums.Category;
import com.buercorp.wangyu.model.TmBook;
import com.buercorp.wangyu.service.BookService;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(BookStoreController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookStoreControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookService bookService;

    @Before
    public void setup(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(new BookStoreController()).build();
    }

    @Test
    public void add() throws Exception{
        //Mockito.doNothing().when(bookService).addBook(Mockito.isA(TmBook.class));
        JsonMapper jsonMapper = new JsonMapper();
        TmBook tmBook = new TmBook();
        tmBook.setBookId(1002);
        tmBook.setTitle("test2");
        tmBook.setAuthor("paddy");
        tmBook.setCategory("2001");
        tmBook.setJpaVersion(0);
        tmBook.setCreatedDatetime(new Date());
        tmBook.setLastModifiedDatetime(new Date());
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/add").contentType(MediaType.APPLICATION_JSON).content(jsonMapper.writeValueAsBytes(tmBook));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk()).andReturn();
    }

}
