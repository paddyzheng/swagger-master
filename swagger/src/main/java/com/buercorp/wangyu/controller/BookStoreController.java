package com.buercorp.wangyu.controller;


import com.buercorp.wangyu.model.enums.CartStatus;
import com.buercorp.wangyu.model.enums.Category;
import com.buercorp.wangyu.model.TmBook;
import com.buercorp.wangyu.model.TmCart;
import com.buercorp.wangyu.service.BookService;
import com.buercorp.wangyu.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Tag(name = "Online-BookStore")
@RestController
@Slf4j
public class BookStoreController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;

    @Operation(summary = "bookManagement-queryBooksByTitleLike")
    @GetMapping("queryBooksByTitleLike")
    public List<TmBook> queryBooksByTitleLike(@Parameter(name = "title", description = "book title") String title) {
        return bookService.queryBooksByTitleLike("%"+title+"%");
    }

    @Operation(summary = "bookManagement-queryBooksByAuthorLike")
    @GetMapping("queryBooksByAuthorLike")
    public List<TmBook> queryBooksByAuthorLike(@Parameter(name = "author", description = "author") String author) {
        return bookService.queryBooksByAuthorLike("%"+author+"%");
    }

    @Operation(summary = "bookManagement-queryBooksByPriceLessThan")
    @GetMapping("queryBooksByPriceLessThan")
    public List<TmBook> queryBooksByPriceLessThan(@Parameter(name = "price", description = "price") BigDecimal price) {
        return bookService.queryBooksByPriceLessThan(price);
    }

    @Operation(summary = "bookManagement-queryBooksByPriceGreaterThan")
    @GetMapping("queryBooksByPriceGreaterThan")
    public List<TmBook> queryBooksByPriceGreaterThan(@Parameter(name = "price", description = "price") BigDecimal price) {
        return bookService.queryBooksByPriceGreaterThan(price);
    }

    @Operation(summary = "bookManagement-list")
    @PostMapping("list")
    public List<TmBook> list() {
        return bookService.getAllBooks();
    }

    @Operation(summary ="bookManagement-add")
    @PostMapping("add")
    public TmBook add(@Parameter(name = "title", description = "book title") String title, @Parameter(name = "author", description = "author") String author,
                      @Parameter(name = "price", description = "price") BigDecimal price, @Parameter(name = "category", description = "category") String category) {
        logger.info("title:{},author:{},price:{},category:{}",title,author,price,category);
        TmBook tmBook = new TmBook();
        tmBook.setTitle(title);
        tmBook.setAuthor(author);
        tmBook.setPrice(price);
        tmBook.setCategory(category);
        tmBook.setJpaVersion(0);
        tmBook.setCreatedDatetime(new Date());
        tmBook.setLastModifiedDatetime(new Date());
        return bookService.addBook(tmBook);
    }

    @Operation(summary ="bookManagement-update")
    @PostMapping("update")
    public TmBook update(@Parameter(name = "bookId", description = "bookId") Integer bookId,@Parameter(name = "title", description = "book title") String title, @Parameter(name = "author", description = "author") String author,
                         @Parameter(name = "price", description = "price") BigDecimal price, @Parameter(name = "category", description = "category") String category) {
        logger.info("bookId:{},title:{},author:{},price:{},category:{}",bookId,title,author,price,category);
        TmBook tmBookFromDb = bookService.queryBookById(bookId);
        if(null!=tmBookFromDb){
            if(StringUtils.isNotBlank(title)){
                tmBookFromDb.setTitle(title);
            }
            if(StringUtils.isNotBlank(author)){
                tmBookFromDb.setAuthor(author);
            }
            if(null!=price){
                tmBookFromDb.setPrice(price);
            }
            if(StringUtils.isNotBlank(category)){
                tmBookFromDb.setCategory(category);
            }
            tmBookFromDb.setJpaVersion(tmBookFromDb.getJpaVersion()+1);
            tmBookFromDb.setCreatedDatetime(tmBookFromDb.getCreatedDatetime());
            tmBookFromDb.setLastModifiedDatetime(new Date());
            return bookService.updateBook(tmBookFromDb);
        }
        return null;
    }

    @Operation(summary ="bookManagement-delete")
    @PostMapping("delete")
    public Boolean delete(@Parameter(name = "bookId", description = "bookId") Integer bookId) {
        logger.info("bookId:{}",bookId);
        bookService.deleteBook(bookId);
        return true;
    }

    @Operation(summary ="shopping cart add")
    @PostMapping("cartAdd")
    public TmCart cart(@Parameter(name = "bookId", description = "bookId") Integer bookId,
                       @Parameter(name = "quantity", description = "quantity") Integer quantity) {
        logger.info("bookId:{},quantity:{}",bookId,quantity);
        TmBook tmBookFromDb = bookService.queryBookById(bookId);
        if(null!=tmBookFromDb){
            TmCart tmCart = new TmCart();
            tmCart.setBookId(bookId);
            tmCart.setTitle(tmBookFromDb.getTitle());
            tmCart.setAuthor(tmBookFromDb.getAuthor());
            tmCart.setPrice(tmBookFromDb.getPrice());
            tmCart.setQuantity(quantity);
            tmCart.setTotalPrice(tmBookFromDb.getPrice().multiply(BigDecimal.valueOf(quantity)));
            tmCart.setStatus(CartStatus.ONLINE);
            tmCart.setJpaVersion(0);
            tmCart.setCreatedDatetime(new Date());
            tmCart.setLastModifiedDatetime(new Date());
            return cartService.addCart(tmCart);
        }
        return null;
    }

    @Operation(summary = "shopping cart list")
    @PostMapping("cartList")
    public List<TmCart> cartList() {
        return cartService.getAllCart();
    }

    @Operation(summary ="shopping cart checkOut")
    @PostMapping("checkOut")
    public TmCart checkOut(Integer cartId) {
        TmCart tmCart = cartService.queryCartById(cartId);
        if(null!=tmCart){
            tmCart.setStatus(CartStatus.CHECKOUT);
            tmCart.setLastModifiedDatetime(new Date());
            return cartService.updateCart(tmCart);
        }
        return null;
    }
}