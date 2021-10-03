package com.example.userservice.service;


import com.example.userservice.VO.Feedback;
import com.example.userservice.VO.ResponseTemplateVO;
import com.example.userservice.entity.User;
import com.example.userservice.repository.UserRepository;
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

    public User saveUser(User user) {
        log.info("Inside saveUser of method UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithFeedback(Long userId) {
        log.info("Inside getUserWithFeedback of method UserService");
        ResponseTemplateVO vo = new ResponseTemplateVO();
        User user = userRepository.findByUserId(userId);

        Feedback feedback = restTemplate.getForObject("http://localhost:8015/feedbacks/" + user.getFeedbackId(), Feedback.class);

        vo.setUser(user);
        vo.setFeedback(feedback);
        return vo;
    }
}
