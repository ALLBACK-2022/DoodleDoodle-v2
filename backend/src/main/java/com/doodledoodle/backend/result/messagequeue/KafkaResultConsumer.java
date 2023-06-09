package com.doodledoodle.backend.result.messagequeue;

import com.doodledoodle.backend.result.dto.kafka.ResultKafkaResponse;
import com.doodledoodle.backend.result.service.ResultService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class KafkaResultConsumer {
    ResultService resultService;
    ObjectMapper objectMapper;

    @SneakyThrows(JsonProcessingException.class)
    @KafkaListener(topics = "doodledoodle.to.backend.result", containerFactory = "kafkaListenerContainerFactory")
    public void consume(String message) {
        resultService.saveResults(objectMapper.readValue(message, ResultKafkaResponse.class));
    }
}
