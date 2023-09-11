package com.doodledoodle.backend.draw.dto.kafka;

import com.doodledoodle.backend.draw.exception.InvalidFileConvertException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class DrawKafkaRequest {
    private UUID drawId;
    private String englishName;
    private String file;

    public DrawKafkaRequest(UUID drawId, String englishName, MultipartFile file) {
        this.drawId = drawId;
        this.englishName = englishName;
        this.file = convertFileToString(file);
    }

    private String convertFileToString(MultipartFile file) {
        try {
            return Base64.getEncoder().encodeToString(file.getBytes());
        } catch (IOException e) {
            throw new InvalidFileConvertException();
        }
    }
}
