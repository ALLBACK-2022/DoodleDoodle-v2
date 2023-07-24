package com.doodledoodle.backend.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DictionaryResultResponse {
    private Long id;
    private Double similarity;
    private String koreanName;
    private String englishName;
    private String imageUrl;
}