package com.doodledoodle.backend.dictionary.mapper;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponse;
import com.doodledoodle.backend.dictionary.entity.Dictionary;
import org.springframework.stereotype.Component;

@Component
public class DictionaryMapper {
    public DictionaryResponse toResponse(final Dictionary dictionary) {
        return DictionaryResponse.builder()
                .koreanName(dictionary.getKoreanName())
                .build();
    }
}
