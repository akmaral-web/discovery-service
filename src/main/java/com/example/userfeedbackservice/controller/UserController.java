package com.example.userfeedbackservice.controller;

import com.example.userfeedbackservice.model.Feedback;
import com.example.userfeedbackservice.model.UserFeedback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/feedback")
public class UserController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserFeedback(@PathVariable Long userId){
        List<Feedback> userFeedback = new ArrayList<>();

        userFeedback.add(Feedback.builder().userId(1L).feedback("High quality and fast delivery").build());
        userFeedback.add(Feedback.builder().userId(2L).feedback("Very bad service").build());
        userFeedback.add(Feedback.builder().userId(3L).feedback("Pretty fine quality").build());

        return ResponseEntity.ok(new UserFeedback(userId, userFeedback));
    }

    @GetMapping("/{feedbackInfo}")
    public Feedback getFeedback(@PathVariable("feedbackInfo") final Long userId, String feedback){
        return new Feedback(userId, feedback);
    }

    @RequestMapping("/{userId}")
    @HystrixCommand(fallbackMethod = "getFallbackUserFeedback",
    threadPoolKey = "feedbackInfo",
    threadPoolProperties = {
            @HystrixProperty(name = "phone", value = "20")
    })
    public Feedback getUser (@PathVariable("userId") Long userId){


        Feedback feedback = restTemplate.getForObject("http://feedback-service/feedbacks/" + user.getFeedbacks(), Feedback.class);
        return getFeedback(1L, "Nice product", 5);
    }

    public Feedback getFallbackUserFeedback (@PathVariable("userId")Long userId){
        return getFeedback(0L, "No comment", 0);
    }
}
