package com.doodledoodle.backend.dictionary.repository;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.global.EntityLoadRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DictionaryRepositoryStandard extends EntityLoadRepository<Dictionary, Long> {
    Optional<Dictionary> findByKoreanName(final String koreanName);

    List<Dictionary> findAllByEnglishNameIn(final Set<String> englishNames);
}
