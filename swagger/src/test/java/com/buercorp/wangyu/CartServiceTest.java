package com.buercorp.wangyu;

import com.buercorp.wangyu.model.TmCart;
import com.buercorp.wangyu.model.enums.CartStatus;
import com.buercorp.wangyu.service.CartService;
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
public class CartServiceTest {

    @Autowired
    private CartService cartService;

    @Before
    public void contextLoads() {
        System.out.println("======================");
    }

    @Test
    public void findAllCart(){
        List<TmCart> tmCartList = cartService.getAllCart();
        Assert.assertEquals(1, tmCartList.size());
    }

    @Test
    public void addCart(){
        TmCart tmCart = new TmCart();
        tmCart.setBookId(1);
        tmCart.setTitle("test2");
        tmCart.setAuthor("paddy");
        tmCart.setPrice(new BigDecimal("200.0"));
        tmCart.setQuantity(5);
        tmCart.setTotalPrice(new BigDecimal("1000.0"));
        tmCart.setStatus(CartStatus.ONLINE);
        tmCart.setJpaVersion(0);
        tmCart.setCreatedDatetime(new Date());
        tmCart.setLastModifiedDatetime(new Date());
        TmCart result = cartService.addCart(tmCart);
        Assert.assertNotNull(result.getCartId());
        Assert.assertEquals("test2",result.getTitle());
        Assert.assertEquals("paddy",result.getAuthor());
        Assert.assertEquals(new BigDecimal("200.0"),result.getPrice());
        Assert.assertEquals("5",result.getQuantity().toString());
        Assert.assertEquals("ONLINE",result.getStatus().toString());
        Assert.assertEquals(new BigDecimal("1000.0"),result.getTotalPrice());
    }

    @Test
    public void updateCart(){
        TmCart tmCart = new TmCart();
        tmCart.setCartId(1);
        tmCart.setBookId(1);
        tmCart.setTitle("test1");
        tmCart.setAuthor("paddy");
        tmCart.setPrice(new BigDecimal("100.0"));
        tmCart.setQuantity(3);
        tmCart.setTotalPrice(new BigDecimal("300.0"));
        tmCart.setStatus(CartStatus.ONLINE);
        tmCart.setJpaVersion(0);
        tmCart.setCreatedDatetime(new Date());
        tmCart.setLastModifiedDatetime(new Date());
        TmCart result = cartService.updateCart(tmCart);
    }

    @Test
    public void deleteBook(){
        TmCart tmCart = new TmCart();
        tmCart.setBookId(1);
        tmCart.setTitle("test2");
        tmCart.setAuthor("paddy");
        tmCart.setPrice(new BigDecimal("200.0"));
        tmCart.setQuantity(6);
        tmCart.setTotalPrice(new BigDecimal("1200.0"));
        tmCart.setStatus(CartStatus.ONLINE);
        tmCart.setJpaVersion(0);
        tmCart.setCreatedDatetime(new Date());
        tmCart.setLastModifiedDatetime(new Date());
        TmCart result = cartService.addCart(tmCart);
        cartService.deleteCart(result.getCartId());
    }
}
