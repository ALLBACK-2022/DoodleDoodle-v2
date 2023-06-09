package com.doodledoodle.backend.result.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;
import java.util.Set;

@Getter
@AllArgsConstructor
public class SimilarityMap {
    private Map<String, Double> similarities;

    public Set<String> getKeySet() {
        return similarities.keySet();
    }

    public Double getSimilarityByKey(String key) {
        return similarities.get(key);
    }
}
