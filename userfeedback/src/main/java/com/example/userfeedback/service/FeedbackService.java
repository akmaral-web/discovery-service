package com.example.userfeedback.service;

import com.example.userfeedback.entity.Feedback;
import com.example.userfeedback.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @PostMapping("/")
    public Feedback saveFeedback(@RequestBody Feedback feedback) {
        return feedbackRepository.save(feedback);
    }
}
