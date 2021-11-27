package com.example.userfeedbackservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

public class Producer {
    public final String topic = "my topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


}
