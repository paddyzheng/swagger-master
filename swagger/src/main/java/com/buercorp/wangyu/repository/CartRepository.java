package com.buercorp.wangyu.repository;

import com.buercorp.wangyu.model.TmCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<TmCart, Integer> {

}
