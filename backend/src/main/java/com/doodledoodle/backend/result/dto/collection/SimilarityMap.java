package com.doodledoodle.backend.result.dto.collection;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class SimilarityMap {
    private final Map<String, Double> similarityMap;

    public Double getSimilarityByEnglishName(final String englishName) {
        return similarityMap.get(englishName);
    }

    public Set<String> getKeySet() {
        return similarityMap.keySet();
    }
}
