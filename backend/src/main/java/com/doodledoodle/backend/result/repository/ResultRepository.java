package com.doodledoodle.backend.result.repository;

import com.doodledoodle.backend.result.entity.Result;

import java.util.List;
import java.util.Optional;

public interface ResultRepository {
    List<Result> findByDrawIdOrderBySimilarityDesc(Long drawId);

    List<Result> findByGameIdOrderBySimilarityDesc(Long gameId);

    <S extends Result> List<S> saveAll(Iterable<S> entities);

    Optional<Result> findById(Long id);
}
