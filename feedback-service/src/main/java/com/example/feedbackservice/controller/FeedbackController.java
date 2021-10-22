package com.example.feedbackservice.controller;

import com.example.feedbackservice.entity.Feedback;
import com.example.feedbackservice.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@Slf4j

public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/")
    public Feedback saveFeedback(@RequestBody Feedback feedback) {
        log.info("Inside saveFeedback method of FeedbackController");
        return feedbackService.saveFeedback(feedback);
    }

    @GetMapping("/{id}")
    public Feedback findFeedbackById(@PathVariable("id") Long feedbackId) {
        log.info("Inside findFeedbackById method of FeedbackController");
        return feedbackService.findFeedbackById(feedbackId);
    }

    @GetMapping
    public String index(){
        return "feedback";
    }


    @GetMapping
    public List<Feedback> getFeedbacks(){
        return Arrays.asList(
                new Feedback(1L, "Not bad, available price", 4),
                new Feedback(2L, "Very bad product. Not recommended", 2),
                new Feedback(3L, "High quality, Works excellent", 5)

        );
    }

    @GetMapping("/{feedbackInfo}")
    public Feedback getFeedback(@PathVariable("feedbackInfo") final Long feedbackId, String comment, int rating){
        return new Feedback(feedbackId, comment, rating);
    }

}
