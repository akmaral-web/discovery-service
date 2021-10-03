package com.example.feedbackservice.controller;

import com.example.feedbackservice.entity.Feedback;
import com.example.feedbackservice.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
