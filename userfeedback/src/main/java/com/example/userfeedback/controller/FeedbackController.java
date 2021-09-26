package com.example.userfeedback.controller;

import com.example.userfeedback.entity.Feedback;
import com.example.userfeedback.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    public Feedback saveFeedback(Feedback feedback){
        return feedbackService.saveFeedback(feedback);
    }
}
