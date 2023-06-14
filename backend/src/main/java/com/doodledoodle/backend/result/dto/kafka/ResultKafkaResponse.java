package com.doodledoodle.backend.result.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultKafkaResponse {
    private Long drawId;
    private Map<String, Double> result;
}
