package com.example.userservice.entity;

import com.example.userservice.VO.Feedback;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Long feedbackId;



    public Feedback getFeedback(@PathVariable("feedbackInfo") final Long feedbackId, String comment, int rating){
        return new Feedback(feedbackId, comment, rating);
    }

    public List<Feedback> getFeedbacks(){

        return Arrays.asList(
                getFeedback(1L, "I like it", 5)
        );}


}

