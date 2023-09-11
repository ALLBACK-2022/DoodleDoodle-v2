package com.doodledoodle.backend.result.entity.collection;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import lombok.Getter;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
public class EnglishNames {
    private final Map<String, Dictionary> englishNameMap;

    public EnglishNames(final List<Dictionary> dictionaries) {
        this.englishNameMap = dictionaries.stream()
                .collect(Collectors.toMap(Dictionary::getEnglishName, Function.identity()));
    }

    public Dictionary getDictionaryByEnglishName(final String key) {
        return englishNameMap.get(key);
    }
}
