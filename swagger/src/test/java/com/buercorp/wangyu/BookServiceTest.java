package com.buercorp.wangyu;

import com.buercorp.wangyu.model.TmBook;
import com.buercorp.wangyu.service.BookService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Before
    public void contextLoads() {
        System.out.println("======================");
    }

    @Test
    public void findAllBooks(){
        List<TmBook> tmBookList = bookService.getAllBooks();
        //Assert.assertEquals(1, tmBookList.size());
    }

    @Test
    public void addBook(){
        TmBook tmBook = new TmBook();
        tmBook.setTitle("test2");
        tmBook.setAuthor("paddy");
        tmBook.setPrice(new BigDecimal("200.0"));
        tmBook.setCategory("2001");
        tmBook.setJpaVersion(0);
        tmBook.setCreatedDatetime(new Date());
        tmBook.setLastModifiedDatetime(new Date());
        TmBook result = bookService.addBook(tmBook);
        Assert.assertNotNull(result.getBookId());
        Assert.assertEquals("test2",result.getTitle());
        Assert.assertEquals("paddy",result.getAuthor());
        Assert.assertEquals(new BigDecimal("200.0"),result.getPrice());
        Assert.assertEquals("2001",result.getCategory());
    }

    @Test
    public void updateBook(){
        TmBook tmBook = new TmBook();
        tmBook.setBookId(1001);
        tmBook.setTitle("test1");
        tmBook.setAuthor("paddy");
        tmBook.setPrice(new BigDecimal("100.0"));
        tmBook.setJpaVersion(0);
        tmBook.setCreatedDatetime(new Date());
        tmBook.setLastModifiedDatetime(new Date());
        TmBook result = bookService.updateBook(tmBook);
        //Assert.assertEquals(1001, result.getBookId());
    }

    @Test
    public void deleteBook(){
        TmBook tmBook = new TmBook();
        tmBook.setTitle("test3");
        tmBook.setAuthor("paddy");
        tmBook.setPrice(new BigDecimal("300.0"));
        tmBook.setJpaVersion(0);
        tmBook.setCreatedDatetime(new Date());
        tmBook.setLastModifiedDatetime(new Date());
        TmBook result = bookService.addBook(tmBook);
        bookService.deleteBook(result.getBookId());
    }

    @Test
    public void queryBooksByTitle(){
        List<TmBook> tmBookList = bookService.queryBooksByTitleLike("%test%");
    }

    @Test
    public void queryBooksByAuthorLike(){
        List<TmBook> tmBookList = bookService.queryBooksByAuthorLike("%paddy%");
    }

    @Test
    public void queryBooksByPriceLessThan(){
        List<TmBook> tmBookList = bookService.queryBooksByPriceLessThan(new BigDecimal("101"));
    }

    @Test
    public void queryBooksByPriceGreaterThan(){
        List<TmBook> tmBookList = bookService.queryBooksByPriceGreaterThan(new BigDecimal("50"));
    }
}
