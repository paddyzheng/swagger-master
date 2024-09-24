package com.buercorp.wangyu.service;

import com.buercorp.wangyu.model.TmCart;
import com.buercorp.wangyu.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public List<TmCart> getAllCart(){
        return cartRepository.findAll();
    }

    public TmCart queryCartById(Integer cartId){
        return cartRepository.getReferenceById(cartId);
    }

    public TmCart addCart(TmCart tmCart){
        return cartRepository.saveAndFlush(tmCart);
    }

    public TmCart updateCart(TmCart tmCart){
        return cartRepository.saveAndFlush(tmCart);
    }

    public void deleteCart(Integer cartId){
        cartRepository.deleteById(cartId);
    }
}
