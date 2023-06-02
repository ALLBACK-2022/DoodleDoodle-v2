package com.doodledoodle.backend.dictionary.repository;

import com.doodledoodle.backend.dictionary.entity.Dictionary;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepository {
    Optional<Dictionary> findByEngName(String engWord);
    Optional<Dictionary> findById(Long id);
    <S extends Dictionary> List<S> saveAll(Iterable<S> entities);
}
