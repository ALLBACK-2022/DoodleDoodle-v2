package com.doodledoodle.backend.result.dto.collection;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class EnglishNameMap {
    private final Map<String, Dictionary> englishNameMap;

    public EnglishNameMap(final List<Dictionary> dictionaries) {
        this.englishNameMap = dictionaries.stream()
                .collect(Collectors.toMap(Dictionary::getEnglishName, Function.identity()));
    }

    public Dictionary getByKey(final String key) {
        return englishNameMap.get(key);
    }
}
