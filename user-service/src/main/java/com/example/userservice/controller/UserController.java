package com.example.userservice.controller;

import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        log.info("Inside saveUser method of UserController");
        return userService.saveUser(user);
    }

    @GetMapping("/{id}")
    public ResponseTemplateVO getUserWithFeedback(@PathVariable("id") Long userId) {
        log.info("Inside saveUser method of UserController");
        return userService.getUserWithFeedback(userId);
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

}

