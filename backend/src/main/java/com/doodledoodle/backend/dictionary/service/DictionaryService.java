package com.doodledoodle.backend.dictionary.service;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponse;
import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.result.dto.collection.EnglishNameMap;
import com.doodledoodle.backend.result.dto.collection.SimilarityMap;

public interface DictionaryService extends EntityLoader<Dictionary, Long> {
    DictionaryResponse getRandomDictionary();

    Dictionary getDictionaryByKoreanName(final String korenName);

    EnglishNameMap getDictionaryMapByEnglishNames(SimilarityMap similarityMap);
}
