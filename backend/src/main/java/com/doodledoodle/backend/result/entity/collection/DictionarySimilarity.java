package com.doodledoodle.backend.result.entity.collection;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import lombok.Getter;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class DictionarySimilarity {
    private final Map<Dictionary, Double> dictionaryMap;

    public DictionarySimilarity(final DrawSimilarity drawSimilarity, final EnglishNames englishNames) {
        this.dictionaryMap = drawSimilarity.getEnglishNames().stream()
                .collect(Collectors.toMap(englishNames::getDictionaryByEnglishName, drawSimilarity::getSimilarityByEnglishName));
    }

    public Set<Dictionary> getDictionaries() {
        return dictionaryMap.keySet();
    }

    public Double getSimilarityByDictionary(final Dictionary key) {
        return dictionaryMap.get(key);
    }
}
