package com.doodledoodle.backend.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DictionaryDetailsResponseDto {
    private Long id;
    private String name;
    private String engName;
    private String imgUrl;
}
