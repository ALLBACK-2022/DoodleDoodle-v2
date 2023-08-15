package com.doodledoodle.backend.game.repository;

import com.doodledoodle.backend.game.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaGameRepository extends JpaRepository<Game, UUID>, GameRepositoryStandard {}
