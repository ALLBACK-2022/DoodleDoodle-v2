package com.doodledoodle.backend.game.repository;

import com.doodledoodle.backend.game.entity.Game;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GameRepository {
    GameRepositoryStandard gameRepositoryStandard;

    public Game save(final Game game) {
        return gameRepositoryStandard.save(game);
    }

    public Optional<Game> findById(final UUID id) {
        return gameRepositoryStandard.findById(id);
    }
}
