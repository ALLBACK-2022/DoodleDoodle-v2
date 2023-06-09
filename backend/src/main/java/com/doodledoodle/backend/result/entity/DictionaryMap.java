package com.doodledoodle.backend.result.entity;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class DictionaryMap {
    private Map<String, Dictionary> dictionaries;

    public Dictionary getDictionaryByKey(String key) {
        return dictionaries.get(key);
    }
}
