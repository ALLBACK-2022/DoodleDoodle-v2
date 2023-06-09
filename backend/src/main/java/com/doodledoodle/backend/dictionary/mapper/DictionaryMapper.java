package com.doodledoodle.backend.dictionary.mapper;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponse;
import com.doodledoodle.backend.dictionary.entity.Dictionary;
import org.springframework.stereotype.Component;

@Component
public class DictionaryMapper {
    public Dictionary toEntity(String koreanName, String englishName, String imgUrl) {
        return Dictionary.builder()
                .koreanName(koreanName)
                .englishName(englishName)
                .imgUrl(imgUrl)
                .build();
    }

    public DictionaryResponse toResponse(Dictionary entity) {
        return DictionaryResponse.builder()
                .word(entity.getKoreanName())
                .build();
    }
}
