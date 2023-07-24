package com.doodledoodle.backend.dictionary.repository;

import com.doodledoodle.backend.dictionary.entity.Dictionary;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DictionaryRepository {
    List<Dictionary> findAllByEnglishNameIn(Set<String> englishNames);

    Optional<Dictionary> findById(Long id);

    <S extends Dictionary> List<S> saveAll(Iterable<S> entities);

    Optional<Dictionary> findByKoreanName(String koreanName);
}
