package com.doodledoodle.backend.draw.messagequeue;

import com.doodledoodle.backend.draw.dto.kafka.DrawKafkaRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class KafkaDrawProducer {
    KafkaTemplate<String, Object> kafkaTemplate;

    public void send(DrawKafkaRequest request) {
        kafkaTemplate.send("doodledoodle.to.ai.draw", "commit", request);
    }
}
