package com.doodledoodle.backend.draw.dto.kafka;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DrawKafkaRequest {
    private Long drawId;
    private String randomWord;
    private MultipartFile file;
}
