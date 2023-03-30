package com.doodledoodle.backend.draw.messagequeue;

import com.doodledoodle.backend.draw.dto.request.DrawKafkaRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaDrawProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(DrawKafkaRequest request) {
        kafkaTemplate.send("doodledoodle.to.ai.draw", "commit", request);
    }
}
