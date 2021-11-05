package com.example.userfeedbackservice.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Feedback {
    private Long userId;
    private String feedback;

}
