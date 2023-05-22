package com.doodledoodle.backend.dictionary.repository;

import com.doodledoodle.backend.dictionary.entity.Dictionary;

import java.util.Optional;

public interface DictionaryQueryRepository {
    Optional<Dictionary> findByEngName(String engWord);
}
