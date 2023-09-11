package com.doodledoodle.backend.dictionary.service;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponse;
import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.result.entity.collection.DrawSimilarity;
import com.doodledoodle.backend.result.entity.collection.EnglishNames;

public interface DictionaryService extends EntityLoader<Dictionary, Long> {
    DictionaryResponse getRandomDictionary();

    Dictionary getDictionaryByKoreanName(final String korenName);

    EnglishNames getDictionaryMapByEnglishNames(final DrawSimilarity drawSimilarity);
}
