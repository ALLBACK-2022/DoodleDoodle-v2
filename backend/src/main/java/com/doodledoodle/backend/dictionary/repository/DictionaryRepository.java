package com.doodledoodle.backend.dictionary.repository;

import com.doodledoodle.backend.dictionary.entity.Dictionary;

import java.util.List;
import java.util.Optional;

public interface DictionaryRepository {
    Optional<Dictionary> findByEngName(String engWord);

//    Optional<Dictionary> findById(Long id);

//    List<Dictionary> saveAll(List<Dictionary> dictionaries);
}
