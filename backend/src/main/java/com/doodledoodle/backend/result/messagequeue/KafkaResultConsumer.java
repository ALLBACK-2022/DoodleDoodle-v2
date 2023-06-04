package com.doodledoodle.backend.result.messagequeue;

import com.doodledoodle.backend.result.service.ResultService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class KafkaResultConsumer {
    ResultService resultService;

    @SneakyThrows(JsonProcessingException.class)
    @KafkaListener(topics = "doodledoodle.to.backend.result", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        ObjectMapper mapper = new ObjectMapper();
        Map<Object, Object> map = mapper.readValue(message, new TypeReference<>() {});

        if (map.isEmpty()) return;

        Long drawId = (Long) map.get("draw_id");
        Map<String, Float> result = (Map<String, Float>) map.get("result");

        resultService.saveResult(drawId, result);
    }
}
