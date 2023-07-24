package com.doodledoodle.backend.draw.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@AllArgsConstructor
public class DrawRequest {
    private Long gameId;
    private Integer playerNo;
    private MultipartFile file;
}
