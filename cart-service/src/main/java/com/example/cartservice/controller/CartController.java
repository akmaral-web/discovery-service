package com.example.cartservice.controller;


import com.example.cartservice.entity.Cart;
import com.example.cartservice.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/carts/hello")
@Slf4j

public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/")
    public Cart saveCart(@RequestBody Cart cart) {
        log.info("Inside saveFeedback method of FeedbackController");
        return cartService.saveCart(cart);
    }

    @GetMapping("/{id}")
    public Cart findCartById(@PathVariable("id") Long cartId) {
        log.info("Inside findFeedbackById method of FeedbackController");
        return cartService.findCartById(cartId);
    }

    @GetMapping
    public String hello(){
        return "HelloWorld";
    }

    @PostMapping("post")
    public String helloPost(@RequestBody final String userAccount){
        return userAccount;
    }

    @PutMapping("put")
    public String helloPut(@RequestBody final String cardNumber){
        return cardNumber;
    }





}
