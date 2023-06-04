package com.doodledoodle.backend.global.init;

import com.doodledoodle.backend.dictionary.service.DictionaryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DictionaryInit {
    DictionaryService dictionaryService;

    @PostConstruct
    public void init() {
        dictionaryService.initializeDictionary();
    }
}
