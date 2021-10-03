package com.example.feedbackservice.service;

import com.example.feedbackservice.entity.Feedback;
import com.example.feedbackservice.repository.FeedbackRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public Feedback saveFeedback(Feedback feedback) {
        log.info("Inside saveFeedback of FeedbackService");
        return feedbackRepository.save(feedback);
    }

    public Feedback findFeedbackById(Long feedbackId) {
        log.info("Inside saveFeedback of FeedbackService");
        return feedbackRepository.findByFeedbackId(feedbackId);
    }


}
