package com.doodledoodle.backend.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class DictionaryResultResponse {
    private UUID id;
    private Double similarity;
    private String koreanName;
    private String englishName;
    private String imageUrl;
}