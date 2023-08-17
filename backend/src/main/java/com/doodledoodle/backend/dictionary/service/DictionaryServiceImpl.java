package com.doodledoodle.backend.dictionary.service;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponse;
import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.mapper.DictionaryMapper;
import com.doodledoodle.backend.dictionary.repository.DictionaryRepository;
import com.doodledoodle.backend.global.exception.EntityNotFoundException;
import com.doodledoodle.backend.result.dto.collection.EnglishNameMap;
import com.doodledoodle.backend.result.dto.collection.SimilarityMap;
import com.doodledoodle.backend.utils.RandomGenerator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DictionaryServiceImpl implements DictionaryService {
    DictionaryRepository dictionaryRepository;
    DictionaryMapper dictionaryMapper;
    RandomGenerator randomGenerator;

    @Override
    public DictionaryResponse getRandomDictionary() {
        return dictionaryMapper.toResponse(loadEntity(randomGenerator.generateRandom()));
    }

    @Override
    public Dictionary getDictionaryByKoreanName(final String koreanName) {
        return dictionaryRepository.findByKoreanName(koreanName)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public EnglishNameMap getDictionaryMapByEnglishNames(final SimilarityMap similarityMap) {
        final Set<String> englishNames = similarityMap.getKeySet();
        final List<Dictionary> dictionaries = dictionaryRepository.findAllByEnglishNameIn(englishNames);
        return new EnglishNameMap(dictionaries);
    }

    @Override
    public Dictionary loadEntity(final Long id) {
        return dictionaryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
}
