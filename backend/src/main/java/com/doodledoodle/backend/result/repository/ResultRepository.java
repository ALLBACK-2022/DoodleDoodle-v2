package com.doodledoodle.backend.result.repository;

import com.doodledoodle.backend.result.entity.Result;

import java.util.List;

public interface ResultRepository {
    List<Result> findByDrawIdOrderBySimilarityDesc(Long drawId);
    List<Result> findByGameIdOrderBySimilarityDesc(Long gameId);
//    List<Result> saveAll(List<Result> results);
}
