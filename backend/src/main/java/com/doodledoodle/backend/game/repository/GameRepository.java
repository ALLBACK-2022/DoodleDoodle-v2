package com.doodledoodle.backend.game.repository;

import com.doodledoodle.backend.game.entity.Game;

import java.util.Optional;

public interface GameRepository {
    Game save(Game game);

    Optional<Game> findById(Long id);
}
