package com.store.catalog.service;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Profile("kafka-enabled")
public class KafkaConsumer {

    @KafkaListener(topics = "kafka-topic-1", groupId = "group-id")
    public void consume(String message) {
        System.out.println("Message received: " + message);
    }
}