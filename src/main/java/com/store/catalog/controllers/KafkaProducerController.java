package com.store.catalog.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class KafkaProducerController {
    @Value("${spring.kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Operation(summary = "Publish a message", description = "Publish a message to the specified Kafka topic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Message sent to topic successfully")
    })
    @PostMapping("/publish")
    public String publishMessage(@RequestParam String message) {
        kafkaTemplate.send(topic, message);
        return "Message sent to topic: " + topic;
    }
}