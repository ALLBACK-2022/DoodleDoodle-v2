package com.doodledoodle.backend.dictionary.mapper;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponseDto;
import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.entity.StorageType;
import org.springframework.stereotype.Component;

@Component
public class DictionaryMapper {
    public Dictionary toEntity(String koWord, String engWord, String imgUrl, StorageType storageType) {
        return Dictionary.builder()
                .name(koWord)
                .engName(engWord)
                .imgUrl(imgUrl)
                .storageType(storageType)
                .build();
    }

    public DictionaryResponseDto toResponse(Dictionary entity) {
        return DictionaryResponseDto.builder()
                .word(entity.getEngName())
                .build();
    }
}
