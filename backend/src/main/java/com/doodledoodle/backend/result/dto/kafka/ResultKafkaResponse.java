package com.doodledoodle.backend.result.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class ResultKafkaResponse {
    private Long drawId;
    private Map<String, Double> result;
}
