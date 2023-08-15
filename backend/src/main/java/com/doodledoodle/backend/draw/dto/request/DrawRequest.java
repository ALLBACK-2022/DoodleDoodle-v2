package com.doodledoodle.backend.draw.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class DrawRequest {
    private UUID gameId;
    private Integer playerNo;
    private MultipartFile file;
}
