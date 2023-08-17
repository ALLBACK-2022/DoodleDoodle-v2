package com.doodledoodle.backend.result.repository;

import com.doodledoodle.backend.result.entity.Result;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ResultRepository {
    ResultRepositoryStandard resultRepositoryStandard;

    public List<Result> findByDrawId(final UUID drawId) {
        return resultRepositoryStandard.findByDrawId(drawId);
    }

    public List<Result> findByGameId(final UUID gameId) {
        return resultRepositoryStandard.findByGameId(gameId);
    }

    public <S extends Result> List<S> saveAll(final Iterable<S> entities) {
        return resultRepositoryStandard.saveAll(entities);
    }

    public Optional<Result> findById(final UUID id) {
        return resultRepositoryStandard.findById(id);
    }
}
