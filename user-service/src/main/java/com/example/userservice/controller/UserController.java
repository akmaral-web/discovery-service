package com.example.userservice.controller;

import com.example.userservice.VO.Feedback;
import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser method of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithFeedback(@PathVariable("id") Long userId) {
        log.info("Inside saveUser method of UserController");
        return userService.getUserFeedback(userId);
    }

    @GetMapping
    public List<User> getUsers(){

        return Arrays.asList(
                new User(1L, "Akmaral", "Shagymgereyeva", "shagymgereyevaa@mail.ru", 10L),
                new User(2L, "Sam", "Simon", "simona@mail.ru", 20L),
                new User(3L, "James", "Charles", "charlie@mail.ru", 30L)

        );
    }


    @GetMapping("/{userInfo}")
    public User getUser(@PathVariable("userInfo") final Long userId, String userName, String lastName, String email, Long feedbackId){
        return new User(userId, userName, lastName, email, feedbackId);
    }

    @GetMapping("/{feedbackInfo}")
    public Feedback getFeedback(@PathVariable("feedbackInfo") final Long feedbackId, String comment, int rating){
        return new Feedback(feedbackId, comment, rating);
    }

    @GetMapping
    public List<Feedback> getFeedbacks(){

        return Arrays.asList(
                getFeedback(1L, "I like it", 5)
        );}


    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackUserFeedback")
    public Feedback getUser (@PathVariable("userId") Long userId){
        User user = userRepository.findByUserId(userId);
        Feedback feedback = restTemplate.getForObject("http://feedback-service/feedbacks/" + user.getFeedbacks(), Feedback.class);
        return getFeedback(1L, "Nice product", 5);
    }

    public Feedback getFallbackUserFeedback (@PathVariable("userId")Long userId){
        return getFeedback(0L, "No comment", 0);
    }

}

