package com.doodledoodle.backend.dictionary.service;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponse;
import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.mapper.DictionaryMapper;
import com.doodledoodle.backend.dictionary.repository.DictionaryRepository;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.result.entity.DictionaryMap;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DictionaryService implements EntityLoader<Dictionary, Long> {
    DictionaryRepository dictionaryRepository;
    DictionaryMapper dictionaryMapper;

    public DictionaryResponse getRandomDictionary() {
        long randomNum = (long) (Math.random() * 99) + 1;
        return dictionaryMapper.toResponse(loadEntity(randomNum));
    }

    public DictionaryMap getEntityListByEngName(Set<String> engNameList) {
        return new DictionaryMap(dictionaryRepository.findAllByEnglishNameIn(engNameList)
                .stream().collect(Collectors.toMap(Dictionary::getEnglishName, Function.identity())));
    }

    @Override
    public Dictionary loadEntity(Long id) {
        return dictionaryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
