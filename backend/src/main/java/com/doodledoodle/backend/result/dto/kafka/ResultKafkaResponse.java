package com.doodledoodle.backend.result.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResultKafkaResponse {
    private UUID drawId;
    private Map<String, Double> result;
    private Map<String, Double> topFive;
}
