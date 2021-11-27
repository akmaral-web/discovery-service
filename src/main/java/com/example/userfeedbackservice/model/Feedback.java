package com.example.userfeedbackservice.model;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class Feedback {
    private Long userId;
    private String feedback;

public  Feedback(Long userId, String feedback){
    this.userId = userId;
    this.feedback = feedback;
}
public  Feedback(){

}

}
