package com.doodledoodle.backend.game.repository;

import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.global.EntityRepository;

import java.util.Optional;
import java.util.UUID;

public interface GameRepositoryStandard extends EntityRepository<Game, UUID> {}
