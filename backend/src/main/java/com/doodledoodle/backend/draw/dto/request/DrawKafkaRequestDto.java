package com.doodledoodle.backend.draw.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class DrawKafkaRequestDto {
    private Long drawId;
    private String randomWord;
    private MultipartFile file;
}
