package com.doodledoodle.backend.dictionary.repository;


import com.doodledoodle.backend.dictionary.entity.Dictionary;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DictionaryRepository {
    private final DictionaryRepositoryStandard dictionaryRepositoryStandard;

    public Optional<Dictionary> findById(final Long id) {
        return dictionaryRepositoryStandard.findById(id);
    }

    public Optional<Dictionary> findByKoreanName(final String koreanName) {
        return dictionaryRepositoryStandard.findByKoreanName(koreanName);
    }

    public List<Dictionary> findAllByEnglishNameIn(final Set<String> englishNames) {
        return dictionaryRepositoryStandard.findAllByEnglishNameIn(englishNames);
    }
}
