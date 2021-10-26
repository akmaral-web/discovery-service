package com.example.userservice.service;


import com.example.userservice.VO.Cart;
import com.example.userservice.VO.Feedback;
import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Feedback getFeedbackById(Long userId){
        return restTemplate.getForObject("http://feedback-service/feedbacks"+ userId, Feedback.class);
    }

    public Cart getCartById(Long userId){
        return restTemplate.getForObject("http://cart-service/carts"+ userId, Cart.class);
    }

    public User saveUser(User user) {
        log.info("Inside saveUser of method UserService");
        return userRepository.save(user);
    }


    public ResponseTemplateVO getUserFeedback(Long userId) {
        log.info("Inside getUserWithFeedback of method UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Feedback feedback = restTemplate.getForObject("http://feedback-service/feedbacks/" + user.getFeedbackId(), Feedback.class);

        Cart cart = restTemplate.getForObject("http://cart-service/carts/" + user.getFeedbackId(), Cart.class);


        vo.setUser(user);
        vo.setFeedback(feedback);

        vo.getUser();
        vo.getFeedback();
        return vo;
    }




}
