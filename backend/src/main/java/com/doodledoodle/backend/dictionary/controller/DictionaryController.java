package com.doodledoodle.backend.dictionary.controller;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponseDto;
import com.doodledoodle.backend.dictionary.service.DictionaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dictionaries")
public class DictionaryController {
    private final DictionaryService dictionaryService;

    @PostConstruct
    public void init() {
        dictionaryService.initializeDictionary();
    }

    @GetMapping("/random-word")
    public ResponseEntity<DictionaryResponseDto> getRandomDictionary() {
        return ResponseEntity.ok(dictionaryService.getRandomDictionary());
    }
}
