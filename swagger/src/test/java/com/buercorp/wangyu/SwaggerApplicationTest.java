package com.buercorp.wangyu;

import com.buercorp.wangyu.service.BookService;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class SwaggerApplicationTest {

    @Autowired
    private BookService bookService;

    @Before
    public void contextLoads() {
        System.out.println("======================");
    }
}
