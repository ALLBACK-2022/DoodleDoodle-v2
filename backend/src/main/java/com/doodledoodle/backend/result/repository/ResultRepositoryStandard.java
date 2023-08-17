package com.doodledoodle.backend.result.repository;

import com.doodledoodle.backend.global.EntityLoadRepository;
import com.doodledoodle.backend.result.entity.Result;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ResultRepositoryStandard extends EntityLoadRepository<Result, UUID> {
    List<Result> findByDrawId(final UUID drawId);

    List<Result> findByGameId(final UUID gameId);

    <S extends Result> List<S> saveAll(final Iterable<S> entities);
}
