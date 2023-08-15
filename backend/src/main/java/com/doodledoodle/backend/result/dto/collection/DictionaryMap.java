package com.doodledoodle.backend.result.dto.collection;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import lombok.Getter;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class DictionaryMap {
    private final Map<Dictionary, Double> dictionaryMap;

    public DictionaryMap(final SimilarityMap similarityMap, final EnglishNameMap englishNameMap) {
        this.dictionaryMap = similarityMap.getKeySet().stream()
                .collect(Collectors.toMap(englishNameMap::getByKey, similarityMap::getSimilarityByEnglishName));
    }

    public Set<Dictionary> getKeySet() {
        return dictionaryMap.keySet();
    }

    public Double getSimilarityByKey(final Dictionary key) {
        return dictionaryMap.get(key);
    }
}
