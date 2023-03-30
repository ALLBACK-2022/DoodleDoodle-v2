package com.doodledoodle.backend.result.messagequeue;

import com.doodledoodle.backend.result.service.ResultService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class KafkaResultConsumer {
    private final ResultService resultService;

    @KafkaListener(topics = "doodledoodle.to.backend.result", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(message, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        if (map.isEmpty()) {
            return;
        }
        Long drawId = (Long) map.get("draw_id");
        Map<String, Float> result = (Map<String, Float>) map.get("result");

        resultService.saveResult(drawId, result);
    }
}
