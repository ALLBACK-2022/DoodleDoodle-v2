package com.doodledoodle.backend.result.repository;

import com.doodledoodle.backend.result.entity.Result;

import java.util.List;

public interface ResultQueryRepository {
    List<Result> findByDrawIdOrderBySimilarityDesc(Long drawId);
    List<Result> findByGameIdOrderBySimilarityDesc(Long gameId);
}
