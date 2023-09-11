package com.doodledoodle.backend.result.entity.collection;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

@RequiredArgsConstructor
public class DrawSimilarity {
    private final Map<String, Double> similarityMap;

    public Double getSimilarityByEnglishName(final String englishName) {
        return similarityMap.get(englishName);
    }

    public Set<String> getEnglishNames() {
        return similarityMap.keySet();
    }
}
