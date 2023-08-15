package com.doodledoodle.backend.dictionary.controller;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponse;
import com.doodledoodle.backend.dictionary.service.DictionaryService;
import com.doodledoodle.backend.dictionary.service.DictionaryServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dictionaries")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DictionaryController {
    DictionaryService dictionaryService;

    @GetMapping("/random-words")
    public ResponseEntity<DictionaryResponse> getRandomDictionary() {
        return ResponseEntity.ok(dictionaryService.getRandomDictionary());
    }
}
