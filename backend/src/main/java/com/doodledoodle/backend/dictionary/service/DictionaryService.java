package com.doodledoodle.backend.dictionary.service;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponseDto;

public interface DictionaryService {
    void initializeDictionary();
    DictionaryResponseDto getRandomDictionary();
}
