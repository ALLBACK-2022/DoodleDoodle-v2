package com.doodledoodle.backend.dictionary.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DictionaryResponse {
    private String koreanName;
}
