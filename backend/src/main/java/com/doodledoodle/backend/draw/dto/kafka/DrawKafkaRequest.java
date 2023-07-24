package com.doodledoodle.backend.draw.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrawKafkaRequest {
    private Long drawId;
    private String randomWord;
}
