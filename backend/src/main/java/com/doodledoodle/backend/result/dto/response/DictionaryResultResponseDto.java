package com.doodledoodle.backend.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DictionaryResultResponseDto {
    private DictionaryDetailsResponseDto dictionary;
    private Float similarity;
}
