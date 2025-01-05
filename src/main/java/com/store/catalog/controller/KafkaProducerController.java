package com.store.catalog.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaProducerController {

    private static final String TOPIC = "kafka-topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping("/publish")
    public String publishMessage(@RequestParam String message) {
        kafkaTemplate.send(TOPIC, message);
        return "Message sent to topic: " + TOPIC;
    }
}
