package com.buercorp.wangyu.service;

import com.buercorp.wangyu.model.TmBook;
import com.buercorp.wangyu.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<TmBook> getAllBooks(){
        return bookRepository.findAll();
    }

    public TmBook addBook(TmBook tmBook){
        return bookRepository.saveAndFlush(tmBook);
    }

    public TmBook updateBook(TmBook tmBook){
        return bookRepository.saveAndFlush(tmBook);
    }

    public void deleteBook(Integer bookId){
        bookRepository.deleteById(bookId);
    }

    public TmBook queryBookById(Integer bookId){
        return bookRepository.getReferenceById(bookId);
    }

    public List<TmBook> queryBooksByTitleLike(String title){
        return bookRepository.findByTitleLike(title);
    }

    public List<TmBook> queryBooksByAuthorLike(String author){
        return bookRepository.findByAuthorLike(author);
    }

    public List<TmBook> queryBooksByPriceLessThan(BigDecimal price){
        return bookRepository.findByPriceLessThan(price);
    }

    public List<TmBook> queryBooksByPriceGreaterThan(BigDecimal price){
        return bookRepository.findByPriceGreaterThan(price);
    }
}
