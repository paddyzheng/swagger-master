package com.buercorp.wangyu.repository;

import com.buercorp.wangyu.model.TmBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface BookRepository extends JpaRepository<TmBook, Integer> {

    List<TmBook> findByTitleLike(String title);
    List<TmBook> findByAuthorLike(String author);
    List<TmBook> findByPriceLessThan(BigDecimal price);
    List<TmBook> findByPriceGreaterThan(BigDecimal price);
}
