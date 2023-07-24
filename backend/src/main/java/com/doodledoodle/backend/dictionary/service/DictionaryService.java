package com.doodledoodle.backend.dictionary.service;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponse;
import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.mapper.DictionaryMapper;
import com.doodledoodle.backend.dictionary.repository.DictionaryRepository;
import com.doodledoodle.backend.global.EntityLoader;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.result.dto.collection.DictionaryMap;
import com.doodledoodle.backend.util.RandomGenerator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DictionaryService implements EntityLoader<Dictionary, Long> {
    DictionaryRepository dictionaryRepository;
    DictionaryMapper dictionaryMapper;
    RandomGenerator randomGenerator;

    public DictionaryResponse getRandomDictionary() {
        return dictionaryMapper.toResponse(loadEntity(randomGenerator.generateRandom()));
    }

    public DictionaryMap getDictionaryMapByEnglishNames(final Set<String> engNameList) {
        return new DictionaryMap(dictionaryRepository.findAllByEnglishNameIn(engNameList)
                .stream().collect(Collectors.toMap(Dictionary::getEnglishName, Function.identity())));
    }

    public Dictionary getDictionary(final String korenName) {
        return dictionaryRepository.findByKoreanName(korenName).orElseThrow((EntityNotFoundException::new));
    }

    @Override
    public Dictionary loadEntity(final Long id) {
        return dictionaryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
