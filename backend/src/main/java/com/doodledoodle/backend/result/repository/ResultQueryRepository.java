package com.doodledoodle.backend.result.repository;

import com.doodledoodle.backend.result.entity.Result;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ResultQueryRepository {
    List<Result> findByDrawId(Long drawId, Sort sort);
    List<Result> findByGameId(Long gameId, Sort sort);
}
