package com.example.cartservice.service;

import com.example.cartservice.entity.Cart;
import com.example.cartservice.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart saveCart(Cart cart) {
        log.info("Inside saveFeedback of FeedbackService");
        return cartRepository.save(cart);
    }

    public Cart findCartById(Long feedbackId) {
        log.info("Inside saveFeedback of FeedbackService");
        return cartRepository.findByCartId(feedbackId);
    }



}
