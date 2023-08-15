package com.doodledoodle.backend.dictionary.repository;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDictionaryRepository extends JpaRepository<Dictionary, Long>, DictionaryRepositoryStandard {}
