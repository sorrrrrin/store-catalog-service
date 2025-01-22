package com.store.catalog.services;

import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Profile("kafka-enabled")
public class KafkaConsumerService {

    @KafkaListener(topics = "${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(String message) {
        System.out.println("Message received: " + message);
    }
}