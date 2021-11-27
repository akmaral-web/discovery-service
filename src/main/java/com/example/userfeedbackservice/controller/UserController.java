package com.example.userfeedbackservice.controller;

import com.example.userfeedbackservice.model.Feedback;
import com.example.userfeedbackservice.model.UserFeedback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/feedback")
public class UserController {



    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserFeedback(@PathVariable Long userId){
        List<Feedback> userFeedback = new ArrayList<>();

        userFeedback.add(Feedback.builder().userId(1L).feedback("High quality and fast delivery").build());
        userFeedback.add(Feedback.builder().userId(2L).feedback("Very bad service").build());
        userFeedback.add(Feedback.builder().userId(3L).feedback("Pretty fine quality").build());

        return ResponseEntity.ok(new UserFeedback(userId, userFeedback));
    }

    //Swagger methods
    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public List<String> getFeedbacks() {
        List<String> feedbackList = new ArrayList<>();
        feedbackList.add("Bad service");
        feedbackList.add("Nice service");
        return feedbackList;
    }
    @RequestMapping(value = "/feedbacks", method = RequestMethod.POST)
    public String createFeedbacks() {
        return "Feedbacks are sent successfully";
    }

    //Hystrix method
    @RequestMapping("/best")
    @HystrixCommand(fallbackMethod = "fallbackBestFeed")
    public List<Feedback> showBestFeed(){
        Feedback feedback = new Feedback();
        feedback.setUserId(99L);
        feedback.setFeedback("High quality");
        return (List<Feedback>) feedback;
   }
  //fallback method
    public List<Feedback> fallbackBestFeed(){
     return (List<Feedback>) new Feedback(0L, "No Feedback");
    }


}
