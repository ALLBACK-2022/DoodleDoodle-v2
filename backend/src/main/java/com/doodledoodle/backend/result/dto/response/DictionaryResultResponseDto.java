package com.doodledoodle.backend.result.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DictionaryResultResponseDto {
    private Long id;
    private Float similarity;
    private String name;
    private String engName;
    private String imgUrl;
}
